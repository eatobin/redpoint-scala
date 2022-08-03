package com.eatobin.redpointscala

import com.eatobin.redpointscala.Players.playersGetGivee

object Rules {
  def giveeNotSelf(selfKey: String, givee: String): Boolean =
    selfKey != givee

  def giveeNotRecip(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val recip = playersGetGivee(givee, giftYear, players)
    selfKey != recip
  }

  def giveeNotRepeat(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val past = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
    val geY = playersGetGivee(selfKey, _: Int, players)
    val geInYrs = past.map(gy => geY(gy))
    !geInYrs.contains(givee)
  }
}
