package object redpoint {

  type RawString = String
  type Scrubbed = String
  type ErrorString = String



  type RosterString = String
  type RosterList = List[List[String]]
  type InfoLine = List[String]
  type PlayersList = List[List[String]]
  type PlayerLine = List[String]

  type PlrSym = Symbol
  type Givee = PlrSym
  type Giver = PlrSym

  type PName = String
  type GiftHist = Vector[GiftPair]
  type GYear = Int

  type RName = String
  type RYear = Int

  type PlayerKV = (PlrSym, Player)
  type PlayersKVList = List[(PlrSym, Player)]
  type PlayersMap = Map[PlrSym, Player]

}
