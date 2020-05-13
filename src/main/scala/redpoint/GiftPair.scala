package redpoint

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  def getGivEeEr(giftPair: GiftPair, eeEr: EeEr): Giv = {
    if (eeEr == Symbol("ee")) giftPair.givee else giftPair.giver
  }

  def setGivEeEr(giftPair: GiftPair, giv: Giv, eEeR: EeEr): GiftPair = {
    if (eEeR == Symbol("ee")) giftPair.copy(givee = giv) else giftPair.copy(giver = giv)
  }
}
