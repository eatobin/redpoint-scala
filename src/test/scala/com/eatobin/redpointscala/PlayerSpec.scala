package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import com.eatobin.redpointscala.Player.{playerJsonStringToPlayer, playerPlayerToJsonString, playerUpdateGiftHistory}
import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class PlayerSpec extends AnyFlatSpec {

  private val jsonString: JsonString = "{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]}"
  private val player: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  "A Player" should "return an updated giftHistory" in {
    assert(playerUpdateGiftHistory(Vector(GiftPair("nope", "yup")))(player) ==
      Player("Paul McCartney", Vector(GiftPair("nope", "yup"))))
  }

  it should "convert from JSON" in {
    assert(playerJsonStringToPlayer(jsonString) == Right(player))
  }

  it should "convert to JSON" in {
    assert(playerPlayerToJsonString(player) == jsonString)
  }
}
