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

  def getGiveePlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Givee =
    getPlayer(players, playerKey).giftHistory(giftYear).givee

  def getGiverPlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Giver =
    getPlayer(players, playerKey).giftHistory(giftYear).giver

  def setGiveePlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Players = {
    val plr = getPlayer(players, playerKey)
    val gh = plr.giftHistory
    val gp = gh(giftYear)
    val ngp = GiftPair.setGivee(gp, givee)
    val ngh = GiftHistory.setGiftPair(gh, giftYear, ngp)
    val nplr = Player.setGiftHistory(plr, ngh)
    setPlayer(players, playerKey, nplr)
  }

  def setGiverPlayers(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giver: Giver): Players = {
    val plr = getPlayer(players, playerKey)
    val gh = plr.giftHistory
    val gp = gh(giftYear)
    val ngp = GiftPair.setGiver(gp, giver)
    val ngh = GiftHistory.setGiftPair(gh, giftYear, ngp)
    val nplr = Player.setGiftHistory(plr, ngh)
    setPlayer(players, playerKey, nplr)
  }
}
