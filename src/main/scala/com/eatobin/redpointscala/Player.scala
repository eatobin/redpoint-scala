package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class Player(playerName: String, giftHistory: Vector[GiftPair])

object Player {
  def playerUpdateGiftHistory(giftHistory: Vector[GiftPair], player: Player): Player =
    player.copy(giftHistory = giftHistory)

  def playerJsonStringToPlayer(plrJsonString: String): Either[Error, Player] =
    decode[Player](plrJsonString)

  def playerPlayerToJsonString(player: Player): JsonString =
    player.asJson.noSpaces
}
