package redpoint

import io.circe.Error
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object GiftHistory {
  def addYear(playerKey: String)(giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def updateGiftHistory(giftYear: Int)(giftPair: GiftPair)(giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory.updated(giftYear, giftPair)

  def jsonStringToGiftHistory(ghString: String): Either[Error, Vector[GiftPair]] =
    decode[Vector[GiftPair]](ghString)

  def giftHistoryToJsonString(giftHistory: Vector[GiftPair]): JsonString =
    giftHistory.asJson.noSpaces
}
