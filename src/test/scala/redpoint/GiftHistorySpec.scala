package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftHistory._

class GiftHistorySpec extends AnyFlatSpec {

  private val giftHistory: GiftHistory = Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")))

  "A GiftHistory" should "add a new year" in {
    assert(giftHistoryAddYear(giftHistory, Symbol("NewBee")) == Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("NewBee"), Symbol("NewBee"))))
  }

  it should "return an updated giftHistory" in {
    assert(giftHistoryUpdateGiftPair(giftHistory, 0, GiftPair(Symbol("me"), Symbol("you"))) == Vector(GiftPair(Symbol("me"), Symbol("you"))))
  }
}
