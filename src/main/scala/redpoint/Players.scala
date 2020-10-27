package redpoint

import redpoint.GiftHistory.{giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import redpoint.GiftPair.{giftPairUpdateGivee, giftPairUpdateGiver}
import redpoint.Player.playerUpdateGiftHistory
import spray.json._

object Players extends DefaultJsonProtocol {
  def playersUpdatePlayer(players: Map[Symbol, Player], playerKey: Symbol, player: Player): Map[Symbol, Player] =
    players.updated(playerKey, player)

  def playersGetPlayerName(players: Map[Symbol, Player], playerKey: Symbol): String =
    players(playerKey).playerName

  def playersAddYear(players: Map[Symbol, Player]): Map[Symbol, Player] = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = giftHistoryAddYear(gh, playerKey)
      val nplr = playerUpdateGiftHistory(player, ngh)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetGivee(players: Map[Symbol, Player], playerKey: Symbol, giftYear: Int): Symbol =
    players(playerKey).giftHistory(giftYear).givee

  def playersGetGiver(players: Map[Symbol, Player], playerKey: Symbol, giftYear: Int): Symbol =
    players(playerKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(players: Map[Symbol, Player], playerKey: Symbol, giftYear: Int, giftPair: GiftPair): Map[Symbol, Player] = {
    val ngh = giftHistoryUpdateGiftHistory(players(playerKey).giftHistory, giftYear, giftPair)
    val nplr = playerUpdateGiftHistory(players(playerKey), ngh)
    playersUpdatePlayer(players, playerKey, nplr)
  }

  def playersUpdateGivee(players: Map[Symbol, Player], playerKey: Symbol, giftYear: Int, givee: Symbol): Map[Symbol, Player] = {
    val ngp = giftPairUpdateGivee(players(playerKey).giftHistory(giftYear), givee)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersUpdateGiver(players: Map[Symbol, Player], playerKey: Symbol, giftYear: Int, giver: Symbol): Map[Symbol, Player] = {
    val ngp = giftPairUpdateGiver(players(playerKey).giftHistory(giftYear), giver)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersJsonStringToPlayers(plrsString: String): Map[Symbol, Player] = plrsString.parseJson.convertTo[Map[Symbol, Player]]
}
