package redpoint

import redpoint.Players.playersGetGivee

object Rules {
  def giveeNotSelf(selfKey: String, givee: String): Boolean =
    selfKey != givee

  def giveeNotRecip(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val recip = playersGetGivee(players, givee, giftYear)
    selfKey != recip
  }

  def giveeNotRepeat(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val past = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
    val geY = playersGetGivee(players, selfKey, _: Int)
    val geInYrs = past.map(gy => geY(gy))
    !geInYrs.contains(givee)
  }
}
