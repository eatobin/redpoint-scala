package redpoint

object Rules {
  def giveeNotSelf(selfKey: String, givee: String): Boolean =
    selfKey != givee

  def giveeNotRecip(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val recip = Players.getGivee(givee)(giftYear)(players)
    selfKey != recip
  }

  def giveeNotRepeat(selfKey: String, givee: String, giftYear: Int, players: Map[String, Player]): Boolean = {
    val past = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
    val geY = Players.getGivee(selfKey)(_: Int)(players)
    val geInYrs = past.map(gy => geY(gy))
    !geInYrs.contains(givee)
  }
}
