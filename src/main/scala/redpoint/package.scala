package object redpoint {
  type Givee = Symbol
  type Giver = Symbol

  type GiftHistory = Vector[GiftPair]
  type PlayerKey = Symbol
  type GiftYear = Int

  type PlayerName = String

  type Players = Map[PlayerKey, Player]

  type RosterName = String
  type RosterYear = Int
}
