package redpoint

object GiftHistory {
  def addYear(giftHistory: GiftHistory, playerKey: PlayerKey): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def getGiftPair(giftHistory: GiftHistory, giftYearT: GiftYear): GiftPair =
    giftHistory(giftYearT)

  def setGiftPair(giftHistory: GiftHistory, giftYearT: GiftYear, giftPair: GiftPair): GiftHistory = {
    giftHistory.updated(giftYearT, giftPair)
  }
}
