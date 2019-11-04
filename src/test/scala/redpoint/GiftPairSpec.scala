package redpoint

import org.scalatest.FlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends FlatSpec {

  private val giftPair: GiftPair = GiftPair('JohLen, 'GeoHar)

  "A GiftPair" should "return its givee" in {
    assert(giftPair.givee == 'JohLen)
  }

  it should "return its giver" in {
    assert(giftPair.giver == 'GeoHar)
  }

  it should "return an updated givEe" in {
    assert(setGivEeEr(giftPair, 'NewBee, 'ee) == GiftPair('NewBee, 'GeoHar))
  }

  it should "return an updated givEr" in {
    assert(setGivEeEr(giftPair, 'NewBee, 'er) == GiftPair('JohLen, 'NewBee))
  }
}
