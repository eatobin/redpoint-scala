package redpoint

object RosterStringCheck {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"

  // Remove the spaces between CSVs and any final \n
  def scrub(rawString: RawString): Scrubbed = {
    rawString
      .stripLineEnd
      .replaceAll(", ", ",")
  }

  // Split string into lines
  def lines(scrubbed: Scrubbed): Array[String] = scrubbed.split('\n')

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
        val mInfoString = nonBlankString(lines(r)(0))
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
        val mRosterName = nonBlankString(lines(r)(0).split(",")(0))
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

  // Ensure that raw-string is scrubbed and fully valid
  def scrubbedRosterString(rawString: RawString): Either[ErrorString, Scrubbed] = {
    namePresent(rosterInfoLinePresent(validLengthString(nonBlankString(rawString))))
  }
}
