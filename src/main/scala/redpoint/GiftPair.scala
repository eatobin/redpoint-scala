package redpoint

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
//  def getGivee(giftPair: GiftPair): Givee = giftPair.givee
//
//  def getGiver(giftPair: GiftPair): Giver = giftPair.giver

  def giftPairSetGivee(giftPair: GiftPair, newGivee: Givee): GiftPair = giftPair.copy(givee = newGivee)

  def giftPairSetGiver(giftPair: GiftPair, newGiver: Giver): GiftPair = giftPair.copy(giver = newGiver)
}
