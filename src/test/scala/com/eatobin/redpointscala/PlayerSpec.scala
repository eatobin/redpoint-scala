package com.eatobin.redpointscala

import com.eatobin.redpointscala.Player.playerUpdateGiftHistory
import org.scalatest.flatspec.AnyFlatSpec

class PlayerSpec extends AnyFlatSpec {

  private val player: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  "A Player" should "return an updated giftHistory" in {
    assert(playerUpdateGiftHistory(Vector(GiftPair("nope", "yup")))(player) ==
      Player("Paul McCartney", Vector(GiftPair("nope", "yup"))))
  }
}
