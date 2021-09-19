package redpoint

import io.circe.Error
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

case class GiftPair(givee: String, giver: String)

object GiftPair {
  def updateGivee(newGivee: String)(giftPair: GiftPair): GiftPair = giftPair.copy(givee = newGivee)

  def updateGiver(newGiver: String)(giftPair: GiftPair): GiftPair = giftPair.copy(giver = newGiver)

  def jsonStringToGiftPair(gpString: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](gpString)

  def giftPairToJsonString(giftPair: GiftPair): JsonString =
    giftPair.asJson.noSpaces
}
