package redpoint

object GiftHistory {
  def giftHistoryAddYear(giftHistory: GiftHistory, playerKey: PlayerKey): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftPair(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory = {
    giftHistory.updated(giftYear, giftPair)
  }
}
