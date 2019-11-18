package redpoint

object Hats {
  def makeHat(players: PlayersT): HatT =
    players.keySet

  def removePuck(hat: HatT, playerKey: PlayerKeyT): HatT =
    hat.filterNot(pk => pk == playerKey)
}
