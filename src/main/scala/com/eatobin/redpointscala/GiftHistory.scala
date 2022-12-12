package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.PlayerKey

object GiftHistory {
  type GiftHistory = Vector[GiftPair]
  type GiftYear = Int

  def giftHistoryAddYear(playerKey: PlayerKey)(giftHistory: GiftHistory): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftYear: GiftYear)(giftPair: GiftPair)(giftHistory: GiftHistory): GiftHistory =
    giftHistory.updated(giftYear, giftPair)
}
