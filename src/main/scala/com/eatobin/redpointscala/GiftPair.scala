package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{Givee, Giver}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  type PlayerSymbol = String
  type Givee = PlayerSymbol
  type Giver = PlayerSymbol
  type JsonString = String

  def giftPairUpdateGivee(givee: Givee)(giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)

  def giftPairUpdateGiver(giver: Giver)(giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)

  def giftPairJsonStringToGiftPair(jsonString: JsonString): Either[Error, GiftPair] =
    decode[GiftPair](jsonString)

  def giftPairGiftPairToJsonString(giftPair: GiftPair): JsonString =
    giftPair.asJson.noSpaces
}
