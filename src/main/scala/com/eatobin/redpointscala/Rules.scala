package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYearTA
import com.eatobin.redpointscala.GiftPair.{GiveeTA, PlayerKeyTA}
import com.eatobin.redpointscala.Players.{Players, playersGetMyGivee}

object Rules {
  def rulesGiveeNotSelf(selfKey: PlayerKeyTA, givee: GiveeTA): Boolean =
    selfKey != givee

  def rulesGiveeNotRecip(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: Players): Boolean = {
    val giveeIsGivingTo = playersGetMyGivee(givee)(players)(giftYear)
    selfKey != giveeIsGivingTo
  }

  def rulesGiveeNotRepeat(selfKey: PlayerKeyTA, givee: GiveeTA, giftYear: GiftYearTA, players: Players): Boolean = {
    val past: Vector[GiftYearTA] = (giftYear - 1).to(giftYear - 4).by(-1).toVector.filterNot(y => y < 0)
    val giveeInYear: GiftYearTA => GiveeTA = (giftYear: GiftYearTA) => playersGetMyGivee(selfKey)(players)(giftYear)
    val giveesInYears: Vector[GiveeTA] = past.map(gy => giveeInYear(gy))
    !giveesInYears.contains(givee)
  }
}
