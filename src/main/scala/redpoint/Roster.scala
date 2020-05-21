package redpoint

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster {
  def getPlayerName(roster: Roster, playerKey: PlayerKey): PlayerName =
    roster.players(playerKey).playerName

  def addYear(roster: Roster): Roster = {
    val nplrs = for ((playerKey, player) <- roster.players) yield
      playerKey -> Player.playerAddYear(player, playerKey)
    Roster(rosterName = roster.rosterName, rosterYear = roster.rosterYear, players = nplrs)
  }

  def getGivee(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): Givee =
    roster.players(playerKey).giftHistory(giftYear).givee

  def getGiver(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear): Giver =
    roster.players(playerKey).giftHistory(giftYear).giver

  private def updatePlayers(roster: Roster, players: Players): Roster =
    roster.copy(players = players)

  private def setGiftPair(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Roster = {
    val ngh = GiftHistory.giftHistoryUpdateGiftPair(roster.players(playerKey).giftHistory, giftYear, giftPair)
    val nplr = Player.playerUpdateGiftHistory(roster.players(playerKey), ngh)
    val nplrs = Players.updatePlayer(roster.players, playerKey, nplr)
    updatePlayers(roster, nplrs)
  }

  def updateGivee(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Roster = {
    val ngp = GiftPair.giftPairUpdateGivee(roster.players(playerKey).giftHistory(giftYear), givee)
    setGiftPair(roster, playerKey, giftYear, ngp)
  }

  def updateGiver(roster: Roster, playerKey: PlayerKey, giftYear: GiftYear, giver: Giver): Roster = {
    val ngp = GiftPair.giftPairUpdateGiver(roster.players(playerKey).giftHistory(giftYear), giver)
    setGiftPair(roster, playerKey, giftYear, ngp)
  }
}
