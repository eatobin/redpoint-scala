package redpoint

object Hats {
  def makeHat(players: Players): Hat =
    players.keySet

  def removePuck(hat: Hat, playerKey: PlayerKey): Hat =
    hat - playerKey

  def discardGivee(discards: Discards, givee: Givee): Discards =
    discards + givee

  def returnDiscards(geHat: Hat, discards: Discards): Hat =
    geHat ++ discards

  def emptyDiscards(): Hat =
    Set()
}
