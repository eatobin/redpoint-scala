package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{JsonString, PlayerSymbol}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

object GiftHistory {
  type GiftHistory = Vector[GiftPair]
  type GiftYear = Int

  def giftHistoryAddYear(playerKey: PlayerSymbol)(giftHistory: GiftHistory): GiftHistory =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftYear: GiftYear)(giftPair: GiftPair)(giftHistory: GiftHistory): GiftHistory =
    giftHistory.updated(giftYear, giftPair)

  def giftHistoryJsonStringToGiftHistory(ghJsonString: String): Either[Error, GiftHistory] =
    decode[Vector[GiftPair]](ghJsonString)

  def giftHistoryGiftHistoryToJsonString(giftHistory: GiftHistory): JsonString =
    giftHistory.asJson.noSpaces
}
