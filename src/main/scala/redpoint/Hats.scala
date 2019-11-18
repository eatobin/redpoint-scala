package redpoint

object Hats {
  def makeHat(players: PlayersT): HatT =
    players.keySet
}
