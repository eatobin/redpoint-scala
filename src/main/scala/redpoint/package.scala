package object redpoint {
//   Entity = InfoLine (String)
  type RawString = String
  type Scrubbed = String
  type ErrorString = String
  type ErrorOrScrubbed = Either[ErrorString, Scrubbed]
  type EntityList = List[List[String]]
//  type RosterLine = String
//  type RosterInfo = RosterLine
//  type Player = List[String]
//  type Roster = List[RosterLine]
  type PlayersAsListOfStringList = List[List[String]]
//type PlayerAsStringList = ???
  type PlayersAsListOfSymbolsLists = List[List[String]]
  type PlayerAsListOfSymbols = List[String]

  type RName = String
  type RYear = Int
  type PlayersList = List[List[String]]
  type PlrSym = Symbol
  type Givee = PlrSym
  type Giver = PlrSym
  type PName = String
  type GiftHist = Vector[GiftPair]
  type PlayerLine = List[String]
  type PlayerKV = (PlrSym, Player)
  type PlayersKVList = List[(PlrSym, Player)]
  type PlayersMap = Map[PlrSym, Player]
  type GYear = Int
}
