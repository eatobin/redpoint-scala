package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

object GiftHistory {
  def addYear(playerKey: JsonString, giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory :+ GiftPair(playerKey, playerKey)

  def updateGiftHistory(giftYear: Int, giftPair: GiftPair, giftHistory: Vector[GiftPair]): Vector[GiftPair] =
    giftHistory.updated(giftYear, giftPair)

  def jsonStringToGiftHistory(ghString: String): Either[Error, Vector[GiftPair]] =
    decode[Vector[GiftPair]](ghString)

  def giftHistoryToJsonString(giftHistory: Vector[GiftPair]): JsonString =
    giftHistory.asJson.noSpaces
}
