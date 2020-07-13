 package redpoint

import spray.json._

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val giftPair: GiftPair = GiftPair(Symbol("JohLen"), Symbol("GeoHar"))

  "A GiftPair" should "update a giver/givee" in {
    assert(giftPairUpdateGivee(giftPair, Symbol("NewBee")) == GiftPair(Symbol("NewBee"), Symbol("GeoHar")))
    assert(giftPairUpdateGiver(giftPair, Symbol("NewBee")) == GiftPair(Symbol("JohLen"), Symbol("NewBee")))
  }

  it should "convert to JSON" in {
    val gpJson = giftPair.toJson
    assert(gpJson == """{"givee":"JohLen","giver":"GeoHar"}""".parseJson)
  }

  it should "convert from JSON" in {
    val gpJson = giftPair.toJson
    assert(gpJson.convertTo[GiftPair] == giftPair)
  }
}
