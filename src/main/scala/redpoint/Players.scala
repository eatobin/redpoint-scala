package redpoint

object Players {
  def playersUpdatePlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)
}
