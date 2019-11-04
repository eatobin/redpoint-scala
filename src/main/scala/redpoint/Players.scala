package redpoint

object Players {
  def getPlayer(players: Players, playerKey: PlayerKey): Player =
    players(playerKey)

  def setPlayer(players: Players, playerKey: PlayerKey, player: Player): Players = {
    players.updated(playerKey, player)
  }

  //  TODO
  def addYearPlayers(players: Players): Players = ???
}
