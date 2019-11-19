package redpoint

object GiftHistory {
  def addYear(giftHistoryT: GiftHistory, playerKeyT: PlayerKey): GiftHistory =
    giftHistoryT :+ GiftPair(playerKeyT, playerKeyT)

  def getGiftPair(giftHistoryT: GiftHistory, giftYearT: GiftYear): GiftPair =
    giftHistoryT(giftYearT)

  def setGiftPair(giftHistoryT: GiftHistory, giftYearT: GiftYear, giftPair: GiftPair): GiftHistory = {
    giftHistoryT.updated(giftYearT, giftPair)
  }
}
