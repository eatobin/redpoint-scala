package redpoint

object GiftHistory {
  def giftHistoryAddYear(giftHistory: GiftHistory, playerKey: PlayerKey): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory = {
    giftHistory.updated(giftYear, giftPair)
  }
}
