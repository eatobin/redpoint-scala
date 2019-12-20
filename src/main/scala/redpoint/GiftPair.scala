package redpoint

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  def setGivEeEr(giftPair: GiftPair, giv: Giv, eEeR: EeEr): GiftPair = {
    if (eEeR == 'ee) giftPair.copy(givee = giv) else giftPair.copy(giver = giv)
  }
}
