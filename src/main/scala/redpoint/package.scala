package object redpoint {

  type RosterString = String
  type RosterList = List[List[String]]
  type RosterLine = List[String]

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
