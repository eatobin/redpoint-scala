package redpoint

import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class GiftHistorySpec extends AnyFlatSpec {

  private val jsonStringGH: JsonString = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
  private val giftHistory: Vector[GiftPair] = Vector(GiftPair("GeoHar", "JohLen"))

  "A GiftHistory" should "add a new year" in {
    assert(GiftHistory.addYear("NewBee")(giftHistory) == Vector(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
  }

  it should "return an updated giftHistory" in {
    assert(GiftHistory.updateGiftHistory(0)(GiftPair("me", "you"))(giftHistory) == Vector(GiftPair("me", "you")))
  }

  it should "convert from JSON" in {
    val ghJson: Either[Error, Vector[GiftPair]] = GiftHistory.jsonStringToGiftHistory(jsonStringGH)
    assert(ghJson == Right(giftHistory))
  }

  it should "convert to JSON" in {
    val ghJson: JsonString = GiftHistory.giftHistoryToJsonString(giftHistory)
    assert(ghJson == jsonStringGH)
  }
}
