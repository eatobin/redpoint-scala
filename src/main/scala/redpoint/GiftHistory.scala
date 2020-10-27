package redpoint

import spray.json._

object GiftHistory extends DefaultJsonProtocol {
  def giftHistoryAddYear(giftHistory: Vector[GiftPair], playerKey: Symbol): Vector[GiftPair] =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftHistory: Vector[GiftPair], giftYear: Int, giftPair: GiftPair): Vector[GiftPair] =
    giftHistory.updated(giftYear, giftPair)

  def giftHistoryJsonStringToGiftHistory(ghString: String): Vector[GiftPair] = ghString.parseJson.convertTo[Vector[GiftPair]]
}
