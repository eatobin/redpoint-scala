package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{JsonStringTA, PlayerKeyTA}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

object GiftHistory {
  type GiftHistory = Vector[GiftPair]
  type GiftYear = Int

  def giftHistoryJsonStringToGiftHistory(jsonString: JsonStringTA): Either[Error, GiftHistory] =
    decode[Vector[GiftPair]](jsonString)

  def giftHistoryAddYear(playerKey: PlayerKeyTA)(giftHistory: GiftHistory): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftYear: GiftYear)(giftPair: GiftPair)(giftHistory: GiftHistory): GiftHistory =
    giftHistory.updated(giftYear, giftPair)
}
