package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{GiveeTA, GiverTA}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

case class GiftPair(givee: GiveeTA, giver: GiverTA)

object GiftPair {
  type PlayerKeyTA = String
  type GiveeTA = PlayerKeyTA
  type GiverTA = PlayerKeyTA
  type JsonStringTA = String

  def giftPairJsonStringToGiftPair(jsonString: JsonStringTA): Either[Error, GiftPair] =
    decode[GiftPair](jsonString)

  def giftPairUpdateGivee(givee: GiveeTA)(giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee)

  def giftPairUpdateGiver(giver: GiverTA)(giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver)
}
