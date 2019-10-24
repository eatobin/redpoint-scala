package object redpoint {
  type Givee = String
  type Giver = String
  type GiftHistory = Vector[GiftPair]
  type PlayerKey = Symbol
  type PlayerName = String
  type Players = Map[PlayerKey, Player]
  type RosterName = String
  type RosterYear = Int
  type GiftYear = Int
}
