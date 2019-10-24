// :paste ./src/main/scala/redpoint/Roster.scala
// redpoint.Roster.getRosterName(redpoint.Roster.ss)

package redpoint

case class GiftPair(givee: Givee, giver: Giver)

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def getGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair =
    giftHistory(giftYear)

  def setGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory =
    giftHistory.updated(giftYear, giftPair)

  def setGiftHistoryInPlayer(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)
}

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster {

}
