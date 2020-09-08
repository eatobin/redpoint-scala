package redpoint

import redpoint.Players.playersGetGivee

object Rules {
  def giveeNotSelf(selfKey: PlayerKey, givee: Givee): Boolean =
    selfKey != givee

  def giveeNotRecip(selfKey: PlayerKey, givee: Givee, giftYear: GiftYear, players: Players): Boolean = {
    val recip = playersGetGivee(players, givee, giftYear)
    selfKey != recip
  }

  def giveeNotRepeat(selfKey: PlayerKey, givee: Givee, giftYear: GiftYear, players: Players): Boolean = {
    val past = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
    val geY = playersGetGivee(players, selfKey, _: GiftYear)
    val geInYrs = past.map(gy => geY(gy))
    !geInYrs.contains(givee)
  }
}
