package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{JsonStringTA, PlayerKeyTA}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

object GiftHistory {
  type GiftHistoryTA = Vector[GiftPair]
  type GiftYearTA = Int

  def giftHistoryJsonStringToGiftHistory(jsonString: JsonStringTA): Either[Error, GiftHistoryTA] =
    decode[Vector[GiftPair]](jsonString)

  def giftHistoryAddYear(playerKey: PlayerKeyTA)(giftHistory: GiftHistoryTA): GiftHistoryTA =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftYear: GiftYearTA)(giftPair: GiftPair)(giftHistory: GiftHistoryTA): GiftHistoryTA =
    giftHistory.updated(giftYear, giftPair)
}
