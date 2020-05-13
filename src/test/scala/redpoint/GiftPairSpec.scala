package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val giftPair: GiftPair = GiftPair('JohLen, 'GeoHar)

  "A GiftPair" should "return its givee" in {
    assert(getGivEeEr(giftPair, Symbol("ee")) == 'JohLen)
  }
  //TODO This should fail
  it should "return its giver" in {
    assert(getGivEeEr(giftPair, Symbol("erX")) == 'GeoHar)
  }

  it should "return an updated givEeEr" in {
    assert(setGivEeEr(giftPair, 'NewBee, 'ee) == GiftPair('NewBee, 'GeoHar))
    assert(setGivEeEr(giftPair, 'NewBee, 'er) == GiftPair('JohLen, 'NewBee))
  }
}
