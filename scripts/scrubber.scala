type RawString = String
type Scrubbed = String
type ErrorString = String

val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"

// Remove the spaces between CSVs and any final \n
def scrub(rawString: RawString): Scrubbed = {
  rawString
    .stripLineEnd
    .replaceAll(", ", ",")
}

// Split string into lines
def lines(scrubbed: Scrubbed): Array[String] = scrubbed.split('\n')

// Remove name from player Array
def removeName(player: Array[String]): Array[String] = player.head +: player.tail.tail

// Ensure string is not nil, empty or only spaces. Returns a scrubbed string
def nonBlankString(rawString: RawString): Either[ErrorString, Scrubbed] = {
  if (rawString == null ||
    rawString
      .trim
      .isEmpty) {
    Left("the roster string was nil, empty or only spaces")
  } else {
    Right(scrub(rawString))
  }
}

// A string of newlines >= 4?
def validLengthString(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      if (r.filter(_ == '\n').length < 4) {
        Left("roster string is not long enough")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}

// Got an info string?
def rosterInfoLinePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      val mInfoString = nonBlankString(lines(r).head)
      if (mInfoString.isLeft) {
        Left("the roster info line is blank")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}

// Got a roster name?
def namePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      val mRosterName = nonBlankString(lines(r).head.split(",").head)
      if (mRosterName.isLeft) {
        Left("the name value is missing")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}

// Got a roster year?
def yearPresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      if (lines(r).head.split(",").length != 2) {
        Left("the year value is missing")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}

// Return the raw-info-string if the year text all digits
def yearTextAllDigits(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      if (!lines(r).head.split(",").last.forall(c => c.isDigit)) {
        Left("the year value is not all digits")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}

// Return the info-string if 1956 <= year <= 2056
def yearInRange(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      val year = lines(r).head.split(",").last.toInt
      if (1956 > year || year > 2056) {
        Left("not 1956 <= year <= 2056")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}

// Given a valid scrubbed-string, return an array of player strings
def makePlayerArrays(scrubbed: Scrubbed): Array[String] = lines(scrubbed).tail

// Returns all player vectors void of names - symbols only
def makeOnlySymbols(playersArray: Array[String]): Array[Array[String]] = {
  playersArray.map(_.split(",")).map(i => removeName(i))
}

// All strings in the arrays are 6 chars long
def allSixChars(playerSymbols: Array[String]): Boolean = {
  val count = playerSymbols.length
  val six = playerSymbols.filter(p => p.length == 6)
  count == 3 && six.length == 3
}

// All of the arrays only symbols
def allArraysAllSix(playerArrays: Array[Array[String]]): Boolean = {
  val theBools = playerArrays.map(a => allSixChars(a))
  theBools.contains(false)
}

// Test
def playersValid(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) =>
      if (allArraysAllSix(makeOnlySymbols(makePlayerArrays(r)))) {
        Left("the players sub-string is invalid")
      } else {
        Right(r)
      }
    case Left(l) =>
      Left(l)
  }
}



// Ensure that raw-string is scrubbed and fully valid
def scrubbedRosterString(rawString: RawString): Either[ErrorString, Scrubbed] = {
  playersValid(yearInRange(yearTextAllDigits(yearPresent(namePresent(rosterInfoLinePresent(validLengthString(nonBlankString(rawString))))))))
}

scrubbedRosterString(bs)
scrubbedRosterString(null)
scrubbedRosterString("The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc")
scrubbedRosterString("\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString(",2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString("The Beatles\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString("The Beatles, 2014P\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString("The Beatles, 2096\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString("The Beatles, 1896\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString("The Beatles, 2014\nRinStaX, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
scrubbedRosterString("The Beatles, 2014\nRinSta, Ringo Starr, JohLen\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
