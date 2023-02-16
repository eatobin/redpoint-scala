package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{GiveeTA, PlayerKey}
import com.eatobin.redpointscala.Players.{Players, playersGetMyGivee}

object Rules {
  def rulesGiveeNotSelf(selfKey: PlayerKey, givee: GiveeTA): Boolean =
    selfKey != givee

  def rulesGiveeNotRecip(selfKey: PlayerKey, givee: GiveeTA, giftYear: GiftYear, players: Players): Boolean = {
    val giveeIsGivingTo = playersGetMyGivee(givee)(players)(giftYear)
    selfKey != giveeIsGivingTo
  }

  def rulesGiveeNotRepeat(selfKey: PlayerKey, givee: GiveeTA, giftYear: GiftYear, players: Players): Boolean = {
    val past: Vector[GiftYear] = (giftYear - 1).to(giftYear - 4).by(-1).toVector.filterNot(y => y < 0)
    val giveeInYear: GiftYear => GiveeTA = (giftYear: GiftYear) => playersGetMyGivee(selfKey)(players)(giftYear)
    val giveesInYears: Vector[GiveeTA] = past.map(gy => giveeInYear(gy))
    !giveesInYears.contains(givee)
  }
}
