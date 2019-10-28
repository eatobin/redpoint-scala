package redpoint

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def getGiftPair(player: Player, giftYear: GiftYear): GiftPair =
    player.giftHistory(giftYear)

  def setGiftPair(player: Player, giftYear: GiftYear, giftPair: GiftPair): Player = {
    val ngh: GiftHistory = player.giftHistory.updated(giftYear, giftPair)
    player.copy(giftHistory = ngh)
  }

}
