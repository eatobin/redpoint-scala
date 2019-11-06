package redpoint

object GiftHistory {
  def addYear(giftHistory: GiftHistoryT, playerKey: PlayerKeyT): GiftHistoryT =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def getGiftPair(giftHistory: GiftHistoryT, giftYear: GiftYearT): GiftPair =
    giftHistory(giftYear)

  def setGiftPair(giftHistory: GiftHistoryT, giftYear: GiftYearT, giftPair: GiftPair): GiftHistoryT = {
    giftHistory.updated(giftYear, giftPair)
  }
}
