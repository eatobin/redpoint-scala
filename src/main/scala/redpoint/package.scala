package object redpoint {
  type RawString = String
  type Scrubbed = String
  type ErrorString = String
  type ErrorOrScrubbed = Either[ErrorString, Scrubbed]
  type EntityList = List[List[String]]
  type PlayersList = List[List[String]]
  type PlayerAsList = List[String]
  type PlayersListSymbols = List[List[String]]
  type PlayerAsListOfSymbols = List[String]

  type RName = String
  type RYear = Int
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
