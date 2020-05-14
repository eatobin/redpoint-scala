package redpoint

case class GiftPair(givee: GivEeEr, giver: GivEeEr)

object GiftPair {
  def getGivee(giftPair: GiftPair): GivEeEr = giftPair.givee

  def getGiver(giftPair: GiftPair): GivEeEr = giftPair.giver

  def setGivee(giftPair: GiftPair, newGivee: GivEeEr): GiftPair = giftPair.copy(givee = newGivee)

  def setGiver(giftPair: GiftPair, newGiver: GivEeEr): GiftPair = giftPair.copy(giver = newGiver)
}
