package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val giftPair: GiftPair = GiftPair(Symbol("JohLen"), Symbol("GeoHar"))

  //  "A GiftPair" should "get its parts" in {
  //    assert(getGivee(giftPair) == Symbol("JohLen"))
  //    assert(getGiver(giftPair) == Symbol("GeoHar"))
  //  }

  "A GiftPair" should "set its parts" in {
    assert(giftPairSetGivee(giftPair, Symbol("NewBee")) == GiftPair(Symbol("NewBee"), Symbol("GeoHar")))
    assert(giftPairSetGiver(giftPair, Symbol("NewBee")) == GiftPair(Symbol("JohLen"), Symbol("NewBee")))
  }
}
