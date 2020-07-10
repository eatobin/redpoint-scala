package redpoint

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster {
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

  def getGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Giver =
    players(playerKey).giftHistory(giftYear).giver

  private def setGiftPair(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Players = {
    val ngh = GiftHistory.giftHistoryUpdateGiftHistory(players(playerKey).giftHistory, giftYear, giftPair)
    val nplr = Player.playerUpdateGiftHistory(players(playerKey), ngh)
    Players.updatePlayer(players, playerKey, nplr)
  }

  def updateGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Players = {
    val ngp = GiftPair.giftPairUpdateGivee(players(playerKey).giftHistory(giftYear), givee)
    setGiftPair(players, playerKey, giftYear, ngp)
  }

  def updateGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giver: Giver): Players = {
    val ngp = GiftPair.giftPairUpdateGiver(players(playerKey).giftHistory(giftYear), giver)
    setGiftPair(players, playerKey, giftYear, ngp)
  }
}
