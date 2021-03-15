package redpoint

import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftPair._

class GiftPairSpec extends AnyFlatSpec {

  private val jsonStringGP: JsonString = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
  private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

  "A GiftPair" should "update a giver/givee" in {
    assert(giftPairUpdateGivee(giftPair)("NewBee") == GiftPair("NewBee", "JohLen"))
    assert(giftPairUpdateGiver(giftPair)("NewBee") == GiftPair("GeoHar", "NewBee"))
  }

  it should "convert from JSON" in {
    val gpJson: Either[Error, GiftPair] = giftPairJsonStringToGiftPair(jsonStringGP)
    assert(gpJson == Right(giftPair))
  }

  it should "convert to JSON" in {
    val gpJson: JsonString = giftPairToJsonString(giftPair)
    assert(gpJson == jsonStringGP)
  }
}
