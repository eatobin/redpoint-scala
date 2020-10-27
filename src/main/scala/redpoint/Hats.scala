package redpoint

object Hats {
  def makeHat(players: Map[Symbol, Player]): Set[Symbol] =
    players.keySet

  def removePuck(hat: Set[Symbol], playerKey: Symbol): Set[Symbol] =
    hat - playerKey

  def discardGivee(discards: Set[Symbol], givee: Symbol): Set[Symbol] =
    discards + givee

  def returnDiscards(geHat: Set[Symbol], discards: Set[Symbol]): Set[Symbol] =
    geHat ++ discards
}
