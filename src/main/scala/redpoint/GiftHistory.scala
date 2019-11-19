package redpoint

object GiftHistory {
  def addYear(giftHistoryT: GiftHistoryT, playerKeyT: PlayerKeyT): GiftHistoryT =
    giftHistoryT :+ GiftPair(playerKeyT, playerKeyT)

  def getGiftPair(giftHistoryT: GiftHistoryT, giftYearT: GiftYearT): GiftPair =
    giftHistoryT(giftYearT)

  def setGiftPair(giftHistoryT: GiftHistoryT, giftYearT: GiftYearT, giftPair: GiftPair): GiftHistoryT = {
    giftHistoryT.updated(giftYearT, giftPair)
  }
}
