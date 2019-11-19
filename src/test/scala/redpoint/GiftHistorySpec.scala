package redpoint

import org.scalatest.FlatSpec
import redpoint.GiftHistory._

class GiftHistorySpec extends FlatSpec {

  private val giftHistoryT: GiftHistoryT = Vector(GiftPair('JohLen, 'GeoHar))

  "A GiftHistory" should "add a new year" in {
    assert(addYear(giftHistoryT, 'NewBee) == Vector(GiftPair('JohLen, 'GeoHar), GiftPair('NewBee, 'NewBee)))
  }

  it should "return a giftPair" in {
    assert(getGiftPair(giftHistoryT, 0) == GiftPair('JohLen, 'GeoHar))
  }

  it should "return an updated giftHistory" in {
    assert(setGiftPair(giftHistoryT, 0, GiftPair('me, 'you)) == Vector(GiftPair('me, 'you)))
  }
}
