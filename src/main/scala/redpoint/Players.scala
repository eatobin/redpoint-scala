package redpoint

object Players {
  def updatePlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)

  def getPlayerName(players: Players, playerKey: PlayerKey): PlayerName =
    players(playerKey).playerName

  def addYear(players: Players): Players = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = GiftHistory.giftHistoryAddYear(gh, playerKey)
      val nplr = Player.playerUpdateGiftHistory(player, ngh)
      playerKey -> nplr
    }
    nplrs
  }

  def getGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Givee =
    players(playerKey).giftHistory(giftYear).givee
}
