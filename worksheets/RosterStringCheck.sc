type RawString = String
type Scrubbed = String
type ErrorString = String
type ErrorOrScrubbed = Either[ErrorString, Scrubbed]
type RosterAsStringList = List[String]

// Remove the spaces between CSVs and any final \n
def scrub(rawString: RawString): Scrubbed = {
  rawString
    .replaceAll(", ", ",")
    .stripLineEnd
}

// Ensure string is not nil, empty or only spaces. Returns a scrubbed string
def nonBlankString(rawString: RawString): ErrorOrScrubbed = {
  if (rawString == null || rawString.trim.isEmpty) {
    Left("the roster string was nil, empty or only spaces")
  } else {
    Right(scrub(rawString))
  }
}

val rs: RawString = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
val validScrubbed: ErrorOrScrubbed = nonBlankString(rs)
val invalidScrubbed: ErrorOrScrubbed = nonBlankString(null)
nonBlankString("")
nonBlankString("   ")

// A string of newlines >= 4?
def validLengthString(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
  errorOrScrubbed match {
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

validLengthString(validScrubbed)
validLengthString(invalidScrubbed)
val badLength = scrub("The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc")
validLengthString(Right(badLength))

// Split scrubbed roster string into lines
def lines(scrubbed: Scrubbed): RosterAsStringList = scrubbed.split('\n').toList

// Got an info string?
def rosterInfoLinePresent(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
  errorOrScrubbed match {
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

rosterInfoLinePresent(validScrubbed)
rosterInfoLinePresent(invalidScrubbed)
val emptyFirstLine = scrub("\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
rosterInfoLinePresent(Right(emptyFirstLine))

// Got a roster name?
def namePresent(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
  errorOrScrubbed match {
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

namePresent(validScrubbed)
namePresent(invalidScrubbed)
val noName = scrub(",2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
namePresent(Right(noName))

// Got a roster year?
def yearPresent(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
  errorOrScrubbed match {
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

yearPresent(validScrubbed)
yearPresent(invalidScrubbed)
val noYear = scrub("The Beatles\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
yearPresent(Right(noYear))

// Return the raw-info-string if the year text all digits
def yearTextAllDigits(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
  errorOrScrubbed match {
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

yearTextAllDigits(validScrubbed)
yearTextAllDigits(invalidScrubbed)
val yearHasLetter = scrub("The Beatles, 2014P\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
yearTextAllDigits(Right(yearHasLetter))

// Return the info-string if 1956 <= year <= 2056
def yearInRange(errorOrScrubbed: ErrorOrScrubbed): ErrorOrScrubbed = {
  errorOrScrubbed match {
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

yearInRange(validScrubbed)
yearInRange(invalidScrubbed)
val yearTooBig = scrub("The Beatles, 2096\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
val yearTooSmall = scrub("The Beatles, 1896\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen")
yearInRange(Right(yearTooBig))
yearInRange(Right(yearTooSmall))









// Remove name from player Array
def removeName(player: List[String]): List[String] = player.head :: player.tail.tail
















// Given a valid scrubbed-string, return an array of player strings
def makePlayerArrays(scrubbed: Scrubbed): List[String] = lines(scrubbed).tail

// Returns all player vectors void of names - symbols only
def makeOnlySymbols(playersArray: List[String]): List[List[String]] = {
  playersArray.map(_.split(",").toList).map(i => removeName(i))
}

// All strings in the arrays are 6 chars long
def allSixChars(playerSymbols: List[String]): Boolean = {
  val count = playerSymbols.length
  val six = playerSymbols.filter(p => p.length == 6)
  count == 3 && six.length == 3
}

// All of the arrays only symbols
def allArraysAllSix(playerArrays: List[List[String]]): Boolean = {
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
  var result = nonBlankString(rawString)
  result = validLengthString(result)
  result = rosterInfoLinePresent(result)
  result = namePresent(result)
  result = yearPresent(result)
  result = yearTextAllDigits(result)
  result = yearInRange(result)
  playersValid(result)
}

scrubbedRosterString(rs)
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
