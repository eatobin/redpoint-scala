package object redpoint {
  type Givee = Symbol
  type Giver = Symbol
  //  type Giv = Symbol
  //  type EeEr = Symbol

  type GiftHistory = Vector[GiftPair]
  type PlayerKey = Symbol
  type GiftYear = Int

  type PlayerName = String

  type Players = Map[PlayerKey, Player]

  type RosterName = String
  type RosterYear = Int

  type Hat = Set[PlayerKey]
  type Discards = Set[PlayerKey]
}
