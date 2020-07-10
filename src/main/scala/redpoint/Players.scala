package redpoint

object Players {
  def updatePlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)

  def getPlayerName(players: Players, playerKey: PlayerKey): PlayerName =
    players(playerKey).playerName
}
