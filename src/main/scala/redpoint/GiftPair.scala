package redpoint

import spray.json._

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair extends DefaultJsonProtocol {
  def giftPairUpdateGivee(giftPair: GiftPair, newGivee: Givee): GiftPair = giftPair.copy(givee = newGivee)

  def giftPairUpdateGiver(giftPair: GiftPair, newGiver: Giver): GiftPair = giftPair.copy(giver = newGiver)

  def giftPairJsonStringToGiftPair(gpString: String): GiftPair = gpString.parseJson.convertTo[GiftPair]

  implicit val giftPairFormat: RootJsonFormat[GiftPair] = jsonFormat2(GiftPair.apply)
}
