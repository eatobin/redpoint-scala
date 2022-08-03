package com.eatobin.redpointscala

import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class GiftPairSpec extends AnyFlatSpec {

  private val jsonStringGP: JsonString = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
  private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

  "A GiftPair" should "update a giver/givee" in {
    assert(GiftPair.updateGivee("NewBee")(giftPair) == GiftPair("NewBee", "JohLen"))
    assert(GiftPair.updateGiver("NewBee")(giftPair) == GiftPair("GeoHar", "NewBee"))
  }

  it should "convert from JSON" in {
    val gpJson: Either[Error, GiftPair] = GiftPair.jsonStringToGiftPair(jsonStringGP)
    assert(gpJson == Right(giftPair))
  }

  it should "convert to JSON" in {
    val gpJson: JsonString = GiftPair.giftPairToJsonString(giftPair)
    assert(gpJson == jsonStringGP)
  }
}
