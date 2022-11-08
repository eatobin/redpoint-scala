package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftHistory
import com.eatobin.redpointscala.GiftPair.JsonString
import com.eatobin.redpointscala.Player.PlayerName
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  type PlayerName = String

  def playerUpdateGiftHistory(giftHistory1: GiftHistory)(player: Player): Player =
    player.copy(giftHistory = giftHistory1)

  def playerJsonStringToPlayer(jsonString: String): Either[Error, Player] =
    decode[Player](jsonString)

  def playerPlayerToJsonString(player: Player): JsonString =
    player.asJson.noSpaces
}
