package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftHistoryTA
import com.eatobin.redpointscala.Player.PlayerName
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

case class Player(playerName: PlayerName, giftHistory: GiftHistoryTA)

object Player {
  type PlayerName = String

  def playerJsonStringToPlayer(jsonString: String): Either[Error, Player] =
    decode[Player](jsonString)

  def playerUpdateGiftHistory(giftHistory1: GiftHistoryTA)(player: Player): Player =
    player.copy(giftHistory = giftHistory1)
}
