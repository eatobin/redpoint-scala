package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class GiftHistorySpec extends AnyFlatSpec {

  private val jsonStringGH: JsonString = "[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]"
  private val giftHistory: Vector[GiftPair] = Vector(GiftPair("GeoHar", "JohLen"))

  "A GiftHistory" should "add a new year" in {
    assert(GiftHistory.giftHistoryAddYear("NewBee", giftHistory) == Vector(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
  }

  it should "return an updated giftHistory" in {
    assert(GiftHistory.giftHistoryUpdateGiftHistory(0, GiftPair("me", "you"), giftHistory) == Vector(GiftPair("me", "you")))
  }

  it should "convert from JSON" in {
    val ghJson: Either[Error, Vector[GiftPair]] = GiftHistory.giftHistoryJsonStringToGiftHistory(jsonStringGH)
    assert(ghJson == Right(giftHistory))
  }

  it should "convert to JSON" in {
    val ghJson: JsonString = GiftHistory.giftHistoryGiftHistoryToJsonString(giftHistory)
    assert(ghJson == jsonStringGH)
  }
}
