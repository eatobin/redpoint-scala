package redpoint

object Hats {
  def makeHat(players: Map[String, Player]): Set[String] =
    players.keySet

  def removePuck(playerKey: String)(hat: Set[String]): Set[String] =
    hat - playerKey

  def discardGivee(givee: String)(discards: Set[String]): Set[String] =
    discards + givee

  def returnDiscards(discards: Set[String])(geHat: Set[String]): Set[String] =
    geHat ++ discards
}
