package redpoint

object Players {
  def playersGetPlayerName(players: Players, playerKey: PlayerKey): PlayerName =
    players(playerKey).playerName

  def playersAddYear(players: Players): Players =
    for ((playerKey, player) <- players) yield
      playerKey -> Player.playerAddYear(player, playerKey)

  private def playersGetGiftPair(players: Players, playerKey: PlayerKey, giftYear: GiftYear): GiftPair = {
    val plr = players(playerKey)
    val gh = plr.giftHistory
    gh(giftYear)
  }

  def playersGetGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Givee = {
    playersGetGiftPair(players, playerKey, giftYear).givee
  }

  def playersGetGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Giver = {
    playersGetGiftPair(players, playerKey, giftYear).giver
  }

  //  def getGivEeErPlayers(players: Players, playerKey: PlayerKey, eEeR: EeEr, giftYear: GiftYear): Giv = {
  //    val plr = getPlayer(players, playerKey)
  //    val gh = plr.giftHistory
  //    val gp = gh(giftYear)
  //    if (eEeR == 'ee) gp.givee else gp.giver
  //  }
  //
  //  def setGivEeErPlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giv: Giv, eEeR: EeEr): Players = {
  //    val plr = getPlayer(players, playerKey)
  //    val gh = plr.giftHistory
  //    val gp = gh(giftYear)
  //    val ngp = GiftPair.setGivEeEr(gp, giv, eEeR)
  //    val ngh = GiftHistory.setGiftPair(gh, giftYear, ngp)
  //    val nplr = Player.setGiftHistory(plr, ngh)
  //    setPlayer(players, playerKey, nplr)
  //  }
  //
  //  def setPlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
  //    players.updated(playerKey, player)
}
