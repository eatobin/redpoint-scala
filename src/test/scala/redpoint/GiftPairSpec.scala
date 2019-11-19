package redpoint

import org.scalatest.FlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends FlatSpec {

  private val giftPair: GiftPair = GiftPair('JohLen, 'GeoHar)

  "A GiftPair" should "return its givee" in {
    assert(giftPair.giveeT == 'JohLen)
  }

  it should "return its giver" in {
    assert(giftPair.giverT == 'GeoHar)
  }

  it should "return an updated givEeEr" in {
    assert(setGivEeEr(giftPair, 'NewBee, 'ee) == GiftPair('NewBee, 'GeoHar))
    assert(setGivEeEr(giftPair, 'NewBee, 'er) == GiftPair('JohLen, 'NewBee))
  }
}
