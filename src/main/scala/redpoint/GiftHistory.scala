package redpoint

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax.EncoderOps

object GiftHistory {
  def giftHistoryAddYear(playerKey: String)(giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftYear: Int)(giftPair: GiftPair)(giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory.updated(giftYear, giftPair)

  def giftHistoryJsonStringToGiftHistory(ghString: String): Either[Error, Vector[GiftPair]] =
    decode[Vector[GiftPair]](ghString)

  def giftHistoryToJsonString(giftHistory: Vector[GiftPair]): JsonString =
    giftHistory.asJson.noSpaces
}
