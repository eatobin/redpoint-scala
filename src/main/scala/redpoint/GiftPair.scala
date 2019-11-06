package redpoint

case class GiftPair(givee: GiveeT, giver: GiverT)

object GiftPair {
  def setGivEeEr(giftPair: GiftPair, giv: GivT, eEeR: EeErT): GiftPair = {
    if (eEeR == 'ee) giftPair.copy(givee = giv) else giftPair.copy(giver = giv)
  }
}
