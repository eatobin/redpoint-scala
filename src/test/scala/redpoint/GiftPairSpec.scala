package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val giftPair: GiftPair = GiftPair(Symbol("JohLen"), Symbol("GeoHar"))

  "A GiftPair" should "get its parts" in {
    assert(getGivee(giftPair) == Symbol("JohLen"))
    assert(getGiver(giftPair) == Symbol("GeoHar"))
  }

  it should "set its parts" in {
    assert(setGivee(giftPair, Symbol("NewBee")) == GiftPair(Symbol("NewBee"), Symbol("GeoHar")))
    assert(setGiver(giftPair, Symbol("NewBee")) == GiftPair(Symbol("JohLen"), Symbol("NewBee")))
  }
}
