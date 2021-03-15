package redpoint

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax.EncoderOps

case class GiftPair(givee: String, giver: String)

object GiftPair {
  def giftPairUpdateGivee(giftPair: GiftPair)(newGivee: String): GiftPair = giftPair.copy(givee = newGivee)

  def giftPairUpdateGiver(giftPair: GiftPair)(newGiver: String): GiftPair = giftPair.copy(giver = newGiver)

  def giftPairJsonStringToGiftPair(gpString: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](gpString)

  def giftPairToJsonString(giftPair: GiftPair): JsonString =
    giftPair.asJson.noSpaces
}
