package redpoint

//import redpoint.Players.playersGetGivee
//
//object Rules {
//  def giveeNotSelf(selfKey: Symbol, givee: Symbol): Boolean =
//    selfKey != givee
//
//  def giveeNotRecip(selfKey: Symbol, givee: Symbol, giftYear: Int, players: Map[Symbol, Player]): Boolean = {
//    val recip = playersGetGivee(players, givee, giftYear)
//    selfKey != recip
//  }
//
//  def giveeNotRepeat(selfKey: Symbol, givee: Symbol, giftYear: Int, players: Map[Symbol, Player]): Boolean = {
//    val past = (giftYear - 1).to(giftYear - 3).by(-1).toVector.filterNot(y => y < 0)
//    val geY = playersGetGivee(players, selfKey, _: Int)
//    val geInYrs = past.map(gy => geY(gy))
//    !geInYrs.contains(givee)
//  }
//}
