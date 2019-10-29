package redpoint

import org.scalatest.FlatSpec
import redpoint.GiftHistory._

class GiftHistorySpec extends FlatSpec {

  private val giftHistory: GiftHistory = Vector(GiftPair('JohLen, 'GeoHar))

  "A GiftHistory" should "add a new year" in {
    assert(addYear(giftHistory, 'NewBee) == Vector(GiftPair('JohLen, 'GeoHar), GiftPair('NewBee, 'NewBee)))
  }

  it should "return a giftPair" in {
    assert(getGiftPair(giftHistory, 0) == GiftPair('JohLen, 'GeoHar))
  }

  //  it should "return an updated givee" in {
  //    assert(setGivee(giftPair, 'NewBee) == GiftPair('NewBee, 'GeoHar))
  //  }
  //
  //  it should "return an updated giver" in {
  //    assert(setGiver(giftPair, 'NewBee) == GiftPair('JohLen, 'NewBee))
  //  }
}
