package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class GiftPair(givee: String, giver: String)

object GiftPair {
  type PlayerSymbol = String
  type Givee = PlayerSymbol
  type Giver = PlayerSymbol
  type JsonString = String

  def giftPairUpdateGivee(gee: Givee, giftPair: GiftPair): GiftPair = giftPair.copy(givee = gee)

  def giftPairUpdateGiver(ger: Giver, giftPair: GiftPair): GiftPair = giftPair.copy(giver = ger)

  def giftPairJsonStringToGiftPair(js: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](js)

  def giftPairGiftPairToJsonString(gp: GiftPair): JsonString =
    gp.asJson.noSpaces
}
