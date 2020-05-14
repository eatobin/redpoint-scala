package redpoint

object GiftHistory {
  def giftHistoryAddYear(giftHistory: GiftHistory, playerKey: PlayerKey): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

//  def getGiftPair(giftHistory: GiftHistory, giftYear: GiftYear): GiftPair =
//    giftHistory(giftYear)

  def giftHistoryUpdateGiftPair(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory = {
    giftHistory.updated(giftYear, giftPair)
  }
}
