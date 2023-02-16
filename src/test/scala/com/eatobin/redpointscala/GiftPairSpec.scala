package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair._
import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class GiftPairSpec extends AnyFlatSpec {

  private val jsonString: JsonStringTA = "{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}"
  private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

  "A GiftPair" should "update a giver/givee" in {
    assert(giftPairUpdateGivee("NewBee")(giftPair) == GiftPair("NewBee", "JohLen"))
    assert(giftPairUpdateGiver("NewBee")(giftPair) == GiftPair("GeoHar", "NewBee"))
  }

  it should "convert from JSON" in {
    val gpJson: Either[Error, GiftPair] = giftPairJsonStringToGiftPair(jsonString)
    assert(gpJson == Right(giftPair))
  }
}
