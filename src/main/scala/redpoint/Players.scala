package redpoint

object Players {
  def getPlayer(players: Players, playerKey: PlayerKey): Player =
    players(playerKey)

  def setPlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)

  def addYearPlayers(players: Players): Players =
    for ((playerKey, player) <- players) yield
      playerKey -> Player.addYearPlayer(player, playerKey)

  def getPlayerNamePlayers(players: Players, playerKey: PlayerKey): PlayerName =
    getPlayer(players, playerKey).playerName
}
