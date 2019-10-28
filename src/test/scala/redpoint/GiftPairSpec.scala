package redpoint

import org.scalatest.FlatSpec

class GiftPairSpec extends FlatSpec {

  private val giftPair: GiftPair = GiftPair("JohLen", "GeoHar")

  "A GiftPair" should "return its givee" in {
    assert(giftPair.givee == "JohLen")
  }

  it should "return its giver" in {
    assert(giftPair.giver == "GeoHar")
  }
}
