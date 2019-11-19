package redpoint

case class GiftPair(giveeT: GiveeT, giverT: GiverT)

object GiftPair {
  def setGivEeEr(giftPair: GiftPair, givT: GivT, eeErT: EeErT): GiftPair = {
    if (eeErT == 'ee) giftPair.copy(giveeT = givT) else giftPair.copy(giverT = givT)
  }
}
