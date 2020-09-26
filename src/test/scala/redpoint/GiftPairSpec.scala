package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val jsonStringGP: String = """{"givee":"JohLen","giver":"GeoHar"}"""
  private val giftPair: GiftPair = GiftPair(Symbol("JohLen"), Symbol("GeoHar"))

  "A GiftPair" should "update a giver/givee" in {
    assert(giftPairUpdateGivee(giftPair, Symbol("NewBee")) == GiftPair(Symbol("NewBee"), Symbol("GeoHar")))
    assert(giftPairUpdateGiver(giftPair, Symbol("NewBee")) == GiftPair(Symbol("JohLen"), Symbol("NewBee")))
  }

  it should "convert from JSON" in {
    val gpJson: GiftPair = giftPairJsonStringToGiftPair(jsonStringGP)
    assert(gpJson == giftPair)
  }
}
