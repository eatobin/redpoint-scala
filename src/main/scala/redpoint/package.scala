import scala.collection.immutable.SortedMap

package object redpoint {
  type Givee = String
  type Giver = String
  type GiftHistory = Vector[GiftPair]
  type PlayerName = String
  type Players = SortedMap[PlayerKey, Player]
  type RosterName = String
  type RosterYear = Int
  type PlayerKey = String
  type GiftYear = Int
}
