package redpoint

object RosterStringCheck {

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

  // Got an info line?
  def rosterInfoLinePresent(eScrubbed: Either[ErrorString, Scrubbed]): Either[ErrorString, Scrubbed] = {
    eScrubbed match {
      case Right(r) => {
        val infoLine = nonBlankString(lines(r)(0))
        if (infoLine.isLeft) {
          Left("the roster info line is blank")
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
    rosterInfoLinePresent(validLengthString(nonBlankString(rawString)))
  }
}
