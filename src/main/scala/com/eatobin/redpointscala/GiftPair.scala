package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class GiftPair(givee: String, giver: String)

object GiftPair {
  def giftPairUpdateGivee(newGivee: String, giftPair: GiftPair): GiftPair = giftPair.copy(givee = newGivee)

  def giftPairUpdateGiver(newGiver: String, giftPair: GiftPair): GiftPair = giftPair.copy(giver = newGiver)

  def giftPairJsonStringToGiftPair(gpJsonString: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](gpJsonString)

  def giftPairGiftPairToJsonString(giftPair: GiftPair): JsonString =
    giftPair.asJson.noSpaces
}
