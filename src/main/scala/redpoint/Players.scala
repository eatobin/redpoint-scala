package redpoint

object Players {
  def addYearPlayers(players: PlayersT): PlayersT =
    for ((playerKey, player) <- players) yield
      playerKey -> Player.addYearPlayer(player, playerKey)

  def getPlayerNamePlayers(players: PlayersT, playerKey: PlayerKeyT): PlayerNameT =
    getPlayer(players, playerKey).playerName

  def getPlayer(players: PlayersT, playerKey: PlayerKeyT): Player =
    players(playerKey)

  def getGivEeErPlayers(players: PlayersT, playerKey: PlayerKeyT, giftYear: GiftYearT, eEeR: EeErT): GivT = {
    val plr = getPlayer(players, playerKey)
    val gh = plr.giftHistory
    val gp = gh(giftYear)
    if (eEeR == 'ee) gp.giveeT else gp.giverT
  }

  def setGivEeErPlayers(players: PlayersT, playerKey: PlayerKeyT, giftYear: GiftYearT, giv: GivT, eEeR: EeErT): PlayersT = {
    val plr = getPlayer(players, playerKey)
    val gh = plr.giftHistory
    val gp = gh(giftYear)
    val ngp = GiftPair.setGivEeEr(gp, giv, eEeR)
    val ngh = GiftHistory.setGiftPair(gh, giftYear, ngp)
    val nplr = Player.setGiftHistory(plr, ngh)
    setPlayer(players, playerKey, nplr)
  }

  def setPlayer(players: PlayersT, playerKey: PlayerKeyT, player: Player): PlayersT =
    players.updated(playerKey, player)
}
