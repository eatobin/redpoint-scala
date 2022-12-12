package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory._
import org.scalatest.flatspec.AnyFlatSpec

class GiftHistorySpec extends AnyFlatSpec {

  private val giftHistory: GiftHistory = Vector(GiftPair("GeoHar", "JohLen"))

  "A GiftHistory" should "add a new year" in {
    assert(giftHistoryAddYear("NewBee")(giftHistory) == Vector(GiftPair("GeoHar", "JohLen"), GiftPair("NewBee", "NewBee")))
  }

  it should "return an updated giftHistory" in {
    assert(giftHistoryUpdateGiftHistory(0)(GiftPair("me", "you"))(giftHistory) == Vector(GiftPair("me", "you")))
  }
}
