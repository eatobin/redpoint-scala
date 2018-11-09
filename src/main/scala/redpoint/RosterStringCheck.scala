package redpoint

object RosterStringCheck {

  // Remove the spaces between CSVs and any final \n
  def scrub(rawString: RawString): Scrubbed = rawString.stripLineEnd.replaceAll(", ", ",")

  // Split string into lines
  def lines(scrubbed: Scrubbed): Array[String] = scrubbed.split('\n')

  // Ensure string is not nil, empty or only spaces. Returns a scrubbed string
  def nonBlankString(rawString: RawString): Either[ErrorString, ResultString] = {
    if (rawString == null || rawString.trim.isEmpty) {
      Left("the roster string was nil, empty or only spaces")
    } else {
      Right(scrub(rawString))
    }
  }

  // A string of newlines >= 4?
  def validLengthString(scrubbed: Scrubbed): Either[ErrorString, ResultString] = {
    if (scrubbed.filter(_ == '\n').length < 4) {
      Left("roster string is not long enough")
    } else {
      Right(scrubbed)
    }
  }
}
