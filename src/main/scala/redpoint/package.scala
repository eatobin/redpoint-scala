package object redpoint {
  type GivEeEr = Symbol

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
