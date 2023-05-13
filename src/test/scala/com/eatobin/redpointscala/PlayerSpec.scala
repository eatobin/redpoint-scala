package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonStringTA
import com.eatobin.redpointscala.Player.{playerJsonStringToPlayer, playerUpdateGiftHistory}
import org.scalatest.flatspec.AnyFlatSpec

class PlayerSpec extends AnyFlatSpec {

  private val jsonString: JsonStringTA =
    "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
  private val player: Player =
    Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  "A Player" should "return an updated giftHistory" in {
    assert(
      playerUpdateGiftHistory(Vector(GiftPair("nope", "yup")))(player) ==
        Player("Paul McCartney", Vector(GiftPair("nope", "yup")))
    )
  }

  it should "convert from JSON" in {
    assert(playerJsonStringToPlayer(jsonString) == Right(player))
  }
}
