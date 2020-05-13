package redpoint

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  def setGivee(giftPair: GiftPair, newGivee: Givee): GiftPair = giftPair.copy(givee = newGivee)

  def setGiver(giftPair: GiftPair, newGiver: Giver): GiftPair = giftPair.copy(giver = newGiver)
}
