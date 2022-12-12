package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftHistory
import com.eatobin.redpointscala.Player.PlayerName

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  type PlayerName = String

  def playerUpdateGiftHistory(giftHistory1: GiftHistory)(player: Player): Player =
    player.copy(giftHistory = giftHistory1)
}
