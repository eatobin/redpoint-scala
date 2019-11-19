package redpoint

case class GiftPair(giveeT: Givee, giverT: Giver)

object GiftPair {
  def setGivEeEr(giftPair: GiftPair, givT: Giv, eeErT: EeEr): GiftPair = {
    if (eeErT == 'ee) giftPair.copy(giveeT = givT) else giftPair.copy(giverT = givT)
  }
}
