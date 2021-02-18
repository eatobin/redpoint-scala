package redpoint

//import org.scalatest.flatspec.AnyFlatSpec
//import redpoint.GiftHistory._
//
//class GiftHistorySpec extends AnyFlatSpec {
//
//  private val jsonStringGH: String = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
//  private val giftHistory: Vector[GiftPair] = Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen")))
//
//  "A GiftHistory" should "add a new year" in {
//    assert(giftHistoryAddYear(giftHistory, Symbol("NewBee")) == Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen")), GiftPair(Symbol("NewBee"), Symbol("NewBee"))))
//  }
//
//  it should "return an updated giftHistory" in {
//    assert(giftHistoryUpdateGiftHistory(giftHistory, 0, GiftPair(Symbol("me"), Symbol("you"))) == Vector(GiftPair(Symbol("me"), Symbol("you"))))
//  }
//
//  it should "convert from JSON" in {
//    val ghJson: Vector[GiftPair] = giftHistoryJsonStringToGiftHistory(jsonStringGH)
//    assert(ghJson == giftHistory)
//  }
//}
