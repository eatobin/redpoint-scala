package redpoint

object Hats {
  def makeHat(players: PlayersT): HatT =
    players.keySet

  def removePuck(hat: HatT, playerKey: PlayerKeyT): HatT =
    hat - playerKey

  def discardGivee(discards: DiscardsT, givee: GiveeT): DiscardsT =
    discards + givee

  def returnDiscards(geHat: HatT, discards: DiscardsT): HatT =
    geHat ++ discards

  def emptyDiscards(): HatT =
    Set()
}
