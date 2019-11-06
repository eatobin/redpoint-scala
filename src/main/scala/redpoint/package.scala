package object redpoint {
  type GiveeT = Symbol
  type GiverT = Symbol
  type GivT = Symbol
  type EeErT = Symbol

  type GiftHistoryT = Vector[GiftPair]
  type PlayerKeyT = Symbol
  type GiftYearT = Int

  type PlayerNameT = String

  type PlayersT = Map[PlayerKeyT, Player]

  type RosterNameT = String
  type RosterYearT = Int
}
