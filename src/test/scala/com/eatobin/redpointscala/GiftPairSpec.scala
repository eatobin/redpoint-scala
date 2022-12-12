package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair._
import org.scalatest.flatspec.AnyFlatSpec

class GiftPairSpec extends AnyFlatSpec {
  private val giftPair: GiftPair = GiftPair("GeoHar", "JohLen")

  "A GiftPair" should "update a giver/givee" in {
    assert(giftPairUpdateGivee("NewBee")(giftPair) == GiftPair("NewBee", "JohLen"))
    assert(giftPairUpdateGiver("NewBee")(giftPair) == GiftPair("GeoHar", "NewBee"))
  }
}
