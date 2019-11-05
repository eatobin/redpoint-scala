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

  def getGivEeErPlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear, eEeR: EeEr): Giv = {
    val plr = getPlayer(players, playerKey)
    val gh = plr.giftHistory
    val gp = gh(giftYear)
    if (eEeR == 'ee) gp.givee else gp.giver
  }

  def setGivEeErPlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giv: Giv, eEeR: EeEr): Players = {
    val plr = getPlayer(players, playerKey)
    val gh = plr.giftHistory
    val gp = gh(giftYear)
    val ngp = GiftPair.setGivEeEr(gp, giv, eEeR)
    val ngh = GiftHistory.setGiftPair(gh, giftYear, ngp)
    val nplr = Player.setGiftHistory(plr, ngh)
    setPlayer(players, playerKey, nplr)
  }
}
