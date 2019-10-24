// :paste ./src/main/scala/redpoint/Roster.scala
// redpoint.Roster.getRosterName(redpoint.Roster.ss)

package redpoint

case class GiftPair(givee: Givee, giver: Giver)

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {

}

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster {

}
