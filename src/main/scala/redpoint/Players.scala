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

  private def playersUpdatePlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)

  private def playersUpdateGiftPair(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Players = {
    val plr = players(playerKey)
    val gh = plr.giftHistory
    val ngh = GiftHistory.giftHistoryUpdateGiftPair(gh, giftYear, giftPair)
    val nplr = Player.playerUpdateGiftHistory(plr, ngh)
    playersUpdatePlayer(players, playerKey, nplr)
  }

  def playersUpdateGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Players = {
    val ngp = GiftPair.giftPairUpdateGivee(playersGetGiftPair(players, playerKey, giftYear), givee)
    playersUpdateGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersUpdateGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giver: Giver): Players = {
    val ngp = GiftPair.giftPairUpdateGiver(playersGetGiftPair(players, playerKey, giftYear), giver)
    playersUpdateGiftPair(players, playerKey, giftYear, ngp)
  }

}
