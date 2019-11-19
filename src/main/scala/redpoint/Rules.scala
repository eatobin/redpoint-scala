package redpoint

import redpoint.Players.getGivEeErPlayers

object Rules {
  def giveeNotSelf(selfKey: PlayerKey, givee: Givee): Boolean =
    selfKey != givee

  def giveeNotRecip(selfKey: PlayerKey, givee: Givee, giftYear: GiftYear, players: Players): Boolean = {
    val recip = getGivEeErPlayers(players, givee, giftYear, 'ee)
    selfKey != recip
  }
}
