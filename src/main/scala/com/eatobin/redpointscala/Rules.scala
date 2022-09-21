package com.eatobin.redpointscala

import com.eatobin.redpointscala.Players.playersGetGivee

object Rules {
  def rulesGiveeNotSelf(selfKey: String, givee: String): Boolean =
    selfKey != givee

  def rulesGiveeNotRecip(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val recip = playersGetGivee(givee, giftYear, players)
    selfKey != recip
  }

  def rulesGiveeNotRepeat(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val past: Vector[Int] = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
    val giveeInYear: Int => String = playersGetGivee(selfKey, _: Int, players)
    val giveesInYears: Vector[String] = past.map(gy => giveeInYear(gy))
    !giveesInYears.contains(givee)
  }
}
