package redpoint

import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftHistory._

class GiftHistorySpec extends AnyFlatSpec {

  private val jsonStringGH: JsonString = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
  private val giftHistory: Vector[GiftPair] = Vector(GiftPair("GeoHar", "JohLen"))

  "A GiftHistory" should "add a new year" in {
    assert(giftHistoryAddYear(giftHistory, "NewBee") == Vector(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
  }

  it should "return an updated giftHistory" in {
    assert(giftHistoryUpdateGiftHistory(giftHistory, 0, GiftPair("me", "you")) == Vector(GiftPair("me", "you")))
  }

  it should "convert from JSON" in {
    val ghJson: Either[Error, Vector[GiftPair]] = giftHistoryJsonStringToGiftHistory(jsonStringGH)
    assert(ghJson == Right(giftHistory))
  }
}
