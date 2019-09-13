type RawString = String
type Scrubbed = String
type ErrorString = String
type ErrorOrScrubbed = Either[ErrorString, Scrubbed]
type RosterAsStringList = List[String]
type Line = String
type LineAsList = List[Line]
type RosterAsLists = List[LineAsList]
type ErrorOrLists = Either[ErrorString, RosterAsLists]

val rs: RawString = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"


// Remove the spaces between CSVs and any final \n
def scrub(rawString: RawString): Scrubbed = {
  rawString
    .replaceAll(", ", ",")
    .stripLineEnd
}
val scrubbed = scrub(rs)

// Ensure string is not nil, empty or only spaces. Returns a scrubbed string
def nonBlankString(rawString: RawString): ErrorOrScrubbed = {
  if (rawString == null || rawString.trim.isEmpty) {
    Left("the roster string was nil, empty or only spaces")
  } else {
    Right(scrub(rawString))
  }
}

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

// Split scrubbed roster string into List[String]
def toRosterAsStringList(scrubbed: Scrubbed): RosterAsStringList = scrubbed.split('\n').toList

def toPlayerList(player: Line): LineAsList = player.split(",").toList

def makeRosterList(rosterAsStringList: RosterAsStringList): ErrorOrLists = {
  Right(rosterAsStringList.map(line => toPlayerList(line)))
}
makeRosterList(toRosterAsStringList(scrubbed))
