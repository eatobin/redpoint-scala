package redpoint

object RosterStringCheck {

  def scrub(rawString: RawString): Scrubbed = rawString.stripLineEnd.replaceAll(", ", ",")

  def lines(scrubbed: Scrubbed): Array[String] = scrubbed.split('\n')

  def nonBlankString(rawString: RawString): Either[ErrorString, ResultString] = {
    if (rawString == null || rawString.trim.isEmpty) {
      Left("The roster string was nil, empty or only spaces")
    } else {
      Right(scrub(rawString))
    }
  }
}
