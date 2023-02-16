package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftHistoryTA
import com.eatobin.redpointscala.Player.PlayerNameTA
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

case class Player(playerName: PlayerNameTA, giftHistory: GiftHistoryTA)

object Player {
  type PlayerNameTA = String

  def playerJsonStringToPlayer(jsonString: String): Either[Error, Player] =
    decode[Player](jsonString)

  def playerUpdateGiftHistory(giftHistory1: GiftHistoryTA)(player: Player): Player =
    player.copy(giftHistory = giftHistory1)
}
