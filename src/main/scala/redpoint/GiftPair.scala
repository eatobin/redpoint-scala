package redpoint

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax.EncoderOps

case class GiftPair(givee: String, giver: String)

object GiftPair {
  def giftPairUpdateGivee(newGivee: String)(giftPair: GiftPair): GiftPair = giftPair.copy(givee = newGivee)

  def giftPairUpdateGiver(newGiver: String)(giftPair: GiftPair): GiftPair = giftPair.copy(giver = newGiver)

  def giftPairJsonStringToGiftPair(gpString: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](gpString)

  def giftPairToJsonString(giftPair: GiftPair): JsonString =
    giftPair.asJson.noSpaces
}
