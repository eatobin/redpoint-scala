package redpoint

import redpoint.GiftHistory.{giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import redpoint.GiftPair.{giftPairUpdateGivee, giftPairUpdateGiver}
import redpoint.Player.playerUpdateGiftHistory
import spray.json._

object Players extends DefaultJsonProtocol {
  def playersUpdatePlayer(players: Players, playerKey: PlayerKey, player: Player): Players =
    players.updated(playerKey, player)

  def playersGetPlayerName(players: Players, playerKey: PlayerKey): PlayerName =
    players(playerKey).playerName

  def playersAddYear(players: Players): Players = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = giftHistoryAddYear(gh, playerKey)
      val nplr = playerUpdateGiftHistory(player, ngh)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Givee =
    players(playerKey).giftHistory(giftYear).givee

  def playersGetGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear): Giver =
    players(playerKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giftPair: GiftPair): Players = {
    val ngh = giftHistoryUpdateGiftHistory(players(playerKey).giftHistory, giftYear, giftPair)
    val nplr = playerUpdateGiftHistory(players(playerKey), ngh)
    playersUpdatePlayer(players, playerKey, nplr)
  }

  def playersUpdateGivee(players: Players, playerKey: PlayerKey, giftYear: GiftYear, givee: Givee): Players = {
    val ngp = giftPairUpdateGivee(players(playerKey).giftHistory(giftYear), givee)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersUpdateGiver(players: Players, playerKey: PlayerKey, giftYear: GiftYear, giver: Giver): Players = {
    val ngp = giftPairUpdateGiver(players(playerKey).giftHistory(giftYear), giver)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }
}
