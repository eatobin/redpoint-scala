package com.eatobin.redpointscala

import com.eatobin.redpointscala.Player.{jsonStringToPlayer, playerToJsonString, playerUpdateGiftHistory}
import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class PlayerSpec extends AnyFlatSpec {

  private val jsonStringPlr: JsonString = "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
  private val player: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  "A Player" should "return an updated giftHistory" in {
    assert(playerUpdateGiftHistory(Vector(GiftPair("nope", "yup")), player) ==
      Player("Paul McCartney", Vector(GiftPair("nope", "yup"))))
  }

  it should "convert from JSON" in {
    val plrJson: Either[Error, Player] = jsonStringToPlayer(jsonStringPlr)
    assert(plrJson == Right(player))
  }

  it should "convert to JSON" in {
    val plrJson: JsonString = playerToJsonString(player)
    assert(plrJson == jsonStringPlr)
  }
}
