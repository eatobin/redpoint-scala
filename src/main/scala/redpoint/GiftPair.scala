package redpoint

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  def giftPairUpdateGivee(giftPair: GiftPair, newGivee: Givee): GiftPair = giftPair.copy(givee = newGivee)

  def giftPairUpdateGiver(giftPair: GiftPair, newGiver: Giver): GiftPair = giftPair.copy(giver = newGiver)
}
