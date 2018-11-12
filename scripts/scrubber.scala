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
    case Right(r) => {
      if (r.filter(_ == '\n').length < 4) {
        Left("roster string is not long enough")
      } else {
        Right(r)
      }
    }
    case Left(l) =>
      Left(l)
  }
}

// Got an info string?
def rosterInfoLinePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) => {
      val mInfoString = nonBlankString(lines(r).head)
      if (mInfoString.isLeft) {
        Left("the roster info line is blank")
      } else {
        Right(r)
      }
    }
    case Left(l) =>
      Left(l)
  }
}

// Got a roster name?
def namePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) => {
      val mRosterName = nonBlankString(lines(r).head.split(",").head)
      if (mRosterName.isLeft) {
        Left("the name value is missing")
      } else {
        Right(r)
      }
    }
    case Left(l) =>
      Left(l)
  }
}

// Got a roster year?
def yearPresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) => {
      if (lines(r).head.split(",").length != 2) {
        Left("the year value is missing")
      } else {
        Right(r)
      }
    }
    case Left(l) =>
      Left(l)
  }
}

// Return the raw-info-string if the year text all digits
def yearTextAllDigits(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) => {
      if (!lines(r).head.split(",").last.forall(c => c.isDigit)) {
        Left("the year value is not all digits")
      } else {
        Right(r)
      }
    }
    case Left(l) =>
      Left(l)
  }
}

// Return the info-string if 1956 <= year <= 2056
def yearInRange(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
  eScrubbed match {
    case Right(r) => {
      val year = lines(r).head.split(",").last.toInt
      if (1956 > year || year > 2056) {
        Left("Not 1956 <= year <= 2056")
      } else {
        Right(r)
      }
    }
    case Left(l) =>
      Left(l)
  }
}

// Given a valid scrubbed-string, return a vector of player vectors
def makePlayerArrays(scrubbed: Scrubbed): Array[Array[String]] = {
  val playersArray = lines(scrubbed).tail
  playersArray.map(_.split(",")).map(i => removeName(i))
}

// Ensure that raw-string is scrubbed and fully valid
def scrubbedRosterString(rawString: RawString): Either[ErrorString, Scrubbed] = {
  yearInRange(yearTextAllDigits(yearPresent(namePresent(rosterInfoLinePresent(validLengthString(nonBlankString(rawString)))))))
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

makePlayerArrays(scrub(bs))
