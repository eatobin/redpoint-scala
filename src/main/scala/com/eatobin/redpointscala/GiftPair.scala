package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class GiftPair(givee: String, giver: String)

object GiftPair {
  def updateGivee(newGivee: JsonString, giftPair: GiftPair): GiftPair = giftPair.copy(givee = newGivee)

  def updateGiver(newGiver: JsonString, giftPair: GiftPair): GiftPair = giftPair.copy(giver = newGiver)

  def jsonStringToGiftPair(gpString: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](gpString)

  def giftPairToJsonString(giftPair: GiftPair): JsonString =
    giftPair.asJson.noSpaces
}
