package redpoint

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def getGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair =
    giftHistory(giftYear)

  def setGiftPairInGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory =
    giftHistory.updated(giftYear, giftPair)

  def setGiftHistoryInPlayer(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)
}
