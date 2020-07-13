package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.GiftHistory._
import spray.json._

class GiftHistorySpec extends AnyFlatSpec {

  private val giftHistory: GiftHistory = Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")))

  "A GiftHistory" should "add a new year" in {
    assert(giftHistoryAddYear(giftHistory, Symbol("NewBee")) == Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("NewBee"), Symbol("NewBee"))))
  }

  it should "return an updated giftHistory" in {
    assert(giftHistoryUpdateGiftHistory(giftHistory, 0, GiftPair(Symbol("me"), Symbol("you"))) == Vector(GiftPair(Symbol("me"), Symbol("you"))))
  }

  it should "convert to JSON" in {
    val ghJson = giftHistory.toJson
    assert(ghJson == """[{"givee":"JohLen","giver":"GeoHar"}]""".parseJson)
  }

  it should "convert from JSON" in {
    val ghJson = giftHistory.toJson
    assert(ghJson.convertTo[GiftHistory] == giftHistory)
  }
}
