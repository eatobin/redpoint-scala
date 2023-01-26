package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, PlayerKey}
import com.eatobin.redpointscala.Players.{Players, playersGetMyGivee}

object Rules {
  def rulesGiveeNotSelf(selfKey: PlayerKey, givee: Givee): Boolean =
    selfKey != givee

  def rulesGiveeNotRecip(selfKey: PlayerKey, givee: Givee, giftYear: GiftYear, players: Players): Boolean = {
    val giveeIsGivingTo = playersGetMyGivee(givee)(players)(giftYear)
    selfKey != giveeIsGivingTo
  }

  def rulesGiveeNotRepeat(selfKey: PlayerKey, givee: Givee, giftYear: GiftYear, players: Players): Boolean = {
    val past: Vector[GiftYear] = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
    val giveeInYear: GiftYear => Givee = (giftYear: GiftYear) => playersGetMyGivee(selfKey)(players)(giftYear)
    val giveesInYears: Vector[Givee] = past.map(gy => giveeInYear(gy))
    !giveesInYears.contains(givee)
  }
}
