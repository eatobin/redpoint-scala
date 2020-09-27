package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val jsonStringGP: String = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
  private val giftPair: GiftPair = GiftPair(Symbol("GeoHar"), Symbol("JohLen"))

  "A GiftPair" should "update a giver/givee" in {
    assert(giftPairUpdateGivee(giftPair, Symbol("NewBee")) == GiftPair(Symbol("NewBee"), Symbol("JohLen")))
    assert(giftPairUpdateGiver(giftPair, Symbol("NewBee")) == GiftPair(Symbol("GeoHar"), Symbol("NewBee")))
  }

  it should "convert from JSON" in {
    val gpJson: GiftPair = giftPairJsonStringToGiftPair(jsonStringGP)
    assert(gpJson == giftPair)
  }
}
