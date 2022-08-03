package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.{giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import com.eatobin.redpointscala.Player.playerUpdateGiftHistory
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

object Players {
  def playersUpdatePlayer(playerKey: JsonString, player: Player, players: Map[JsonString, Player]): Map[String, Player] =
    players.updated(playerKey, player)

  def playersGetPlayerName(playerKey: JsonString, players: Map[JsonString, Player]): String =
    players(playerKey).playerName

  def playersAddYear(players: Map[String, Player]): Map[String, Player] = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = giftHistoryAddYear(playerKey, gh)
      val nplr = playerUpdateGiftHistory(ngh, player)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetGivee(selfKey: JsonString, giftYear: Int, players: Map[JsonString, Player]): String =
    players(selfKey).giftHistory(giftYear).givee

  def playersGetGiver(selfKey: JsonString, giftYear: Int, players: Map[JsonString, Player]): String =
    players(selfKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(playerKey: JsonString, giftYear: Int, giftPair: GiftPair, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngh = giftHistoryUpdateGiftHistory(giftYear, giftPair, players(playerKey).giftHistory)
    val nplr = playerUpdateGiftHistory(ngh, players(playerKey))
    playersUpdatePlayer(playerKey, nplr, players)
  }

  def playersUpdateGivee(selfKey: JsonString, giftYear: Int, givee: JsonString, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngp = GiftPair.giftPairUpdateGivee(givee, players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey, giftYear, ngp, players)
  }

  def playersUpdateGiver(selfKey: JsonString, giftYear: Int, giver: JsonString, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngp = GiftPair.giftPairUpdateGiver(giver, players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey, giftYear, ngp, players)
  }

  def jsonStringToPlayers(plrsString: String): Either[Error, Map[String, Player]] =
    decode[Map[String, Player]](plrsString)
}
