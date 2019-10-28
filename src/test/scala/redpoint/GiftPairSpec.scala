package redpoint

import org.scalatest.FlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends FlatSpec {

  private val giftPair: GiftPair = GiftPair("JohLen", "GeoHar")

  "A GiftPair" should "return its givee" in {
    assert(giftPair.givee == "JohLen")
  }

  it should "return its giver" in {
    assert(giftPair.giver == "GeoHar")
  }

  it should "return an updated givee" in {
    assert(setGivee(giftPair, "NewBee") == GiftPair("NewBee", "GeoHar"))
  }
}
