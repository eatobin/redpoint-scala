package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{Givee, Giver}

case class GiftPair(givee: Givee, giver: Giver)

object GiftPair {
  type PlayerKey = String
  type Givee = PlayerKey
  type Giver = PlayerKey

  def giftPairUpdateGivee(givee1: Givee)(giftPair: GiftPair): GiftPair = giftPair.copy(givee = givee1)

  def giftPairUpdateGiver(giver1: Giver)(giftPair: GiftPair): GiftPair = giftPair.copy(giver = giver1)
}
