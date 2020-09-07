package redpoint

import spray.json._

object Players extends DefaultJsonProtocol {
  def playersUpdatePlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)

  def playersGetPlayerName(players: Players, playerKey: PlayerKey): PlayerName =
    players(playerKey).playerName

  def playersAddYear(players: Players): Players = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = GiftHistory.giftHistoryAddYear(gh, playerKey)
      val nplr = Player.playerUpdateGiftHistory(player, ngh)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Givee =
    players(playerKey).giftHistory(giftYear).givee

  def playersGetGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Giver =
    players(playerKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Players = {
    val ngh = GiftHistory.giftHistoryUpdateGiftHistory(players(playerKey).giftHistory, giftYear, giftPair)
    val nplr = Player.playerUpdateGiftHistory(players(playerKey), ngh)
    Players.playersUpdatePlayer(players, playerKey, nplr)
  }

  def playersUpdateGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Players = {
    val ngp = GiftPair.giftPairUpdateGivee(players(playerKey).giftHistory(giftYear), givee)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersUpdateGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giver: Giver): Players = {
    val ngp = GiftPair.giftPairUpdateGiver(players(playerKey).giftHistory(giftYear), giver)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }
}
