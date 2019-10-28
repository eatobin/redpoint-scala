package redpoint

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  def setGivee(giftPair: GiftPair, givee: Givee): GiftPair = giftPair.copy(givee = givee)

  def setGiver(giftPair: GiftPair, giver: Giver): GiftPair = giftPair.copy(giver = giver)
}
