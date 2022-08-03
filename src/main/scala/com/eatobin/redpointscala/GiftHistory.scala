package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

object GiftHistory {
  def giftHistoryAddYear(playerKey: String, giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def giftHistoryUpdateGiftHistory(giftYear: Int, giftPair: GiftPair, giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory.updated(giftYear, giftPair)

  def giftHistoryJsonStringToGiftHistory(ghJsonString: String): Either[Error, Vector[GiftPair]] =
    decode[Vector[GiftPair]](ghJsonString)

  def giftHistoryGiftHistoryToJsonString(giftHistory: Vector[GiftPair]): JsonString =
    giftHistory.asJson.noSpaces
}
