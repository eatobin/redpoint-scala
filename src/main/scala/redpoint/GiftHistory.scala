package redpoint

import spray.json._
import spray.json.DefaultJsonProtocol._

object GiftHistory extends DefaultJsonProtocol {
  def giftHistoryAddYear(giftHistory: GiftHistory, playerKey: PlayerKey): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory = {
    giftHistory.updated(giftYear, giftPair)
  }
}
