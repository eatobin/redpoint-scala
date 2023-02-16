package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory._
import com.eatobin.redpointscala.GiftPair.JsonStringTA
import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class GiftHistorySpec extends AnyFlatSpec {

  private val jsonString: JsonStringTA = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
  private val giftHistory: GiftHistory = Vector(GiftPair("GeoHar", "JohLen"))

  "A GiftHistory" should "add a new year" in {
    assert(giftHistoryAddYear("NewBee")(giftHistory) == Vector(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
  }

  it should "return an updated giftHistory" in {
    assert(giftHistoryUpdateGiftHistory(0)(GiftPair("me", "you"))(giftHistory) == Vector(GiftPair("me", "you")))
  }

  it should "convert from JSON" in {
    val ghJson: Either[Error, Vector[GiftPair]] = giftHistoryJsonStringToGiftHistory(jsonString)
    assert(ghJson == Right(giftHistory))
  }
}
