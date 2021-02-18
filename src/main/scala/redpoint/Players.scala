package redpoint

import io.circe.Error
import io.circe.generic.decoding.DerivedDecoder.deriveDecoder
import io.circe.parser._
import redpoint.GiftHistory.{giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import redpoint.GiftPair.{giftPairUpdateGivee, giftPairUpdateGiver}
import redpoint.Player.playerUpdateGiftHistory

object Players {
  def playersUpdatePlayer(players: Map[String, Player], playerKey: String, player: Player): Map[String, Player] =
    players.updated(playerKey, player)

  def playersGetPlayerName(players: Map[String, Player], playerKey: String): String =
    players(playerKey).playerName

  def playersAddYear(players: Map[String, Player]): Map[String, Player] = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = giftHistoryAddYear(gh, playerKey)
      val nplr = playerUpdateGiftHistory(player, ngh)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetGivee(players: Map[String, Player], playerKey: String, giftYear: Int): String =
    players(playerKey).giftHistory(giftYear).givee

  def playersGetGiver(players: Map[String, Player], playerKey: String, giftYear: Int): String =
    players(playerKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(players: Map[String, Player], playerKey: String, giftYear: Int, giftPair: GiftPair): Map[String, Player] = {
    val ngh = giftHistoryUpdateGiftHistory(players(playerKey).giftHistory, giftYear, giftPair)
    val nplr = playerUpdateGiftHistory(players(playerKey), ngh)
    playersUpdatePlayer(players, playerKey, nplr)
  }

  def playersUpdateGivee(players: Map[String, Player], playerKey: String, giftYear: Int, givee: String): Map[String, Player] = {
    val ngp = giftPairUpdateGivee(players(playerKey).giftHistory(giftYear), givee)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersUpdateGiver(players: Map[String, Player], playerKey: String, giftYear: Int, giver: String): Map[String, Player] = {
    val ngp = giftPairUpdateGiver(players(playerKey).giftHistory(giftYear), giver)
    playersSetGiftPair(players, playerKey, giftYear, ngp)
  }

  def playersJsonStringToPlayers(plrsString: String): Either[Error, Map[String, Player]] =
    decode[Map[String, Player]](plrsString)
}
