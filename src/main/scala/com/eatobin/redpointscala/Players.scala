package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.{giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import com.eatobin.redpointscala.GiftPair.{JsonString, PlayerKey}
import com.eatobin.redpointscala.Player.playerUpdateGiftHistory
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

object Players {
  type SelfKey = PlayerKey
  type Players = Map[String, Player]

  def playersUpdatePlayer(playerKey: PlayerKey, player: Player, players: Map[String, Player]): Map[String, Player] =
    players.updated(playerKey, player)

  def playersGetPlayerName(playerKey: PlayerKey, players: Map[String, Player]): String =
    players(playerKey).playerName

  def playersAddYear(players: Map[String, Player]): Map[String, Player] = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = giftHistoryAddYear(playerKey)(gh)
      val nplr = playerUpdateGiftHistory(ngh)(player)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetGivee(selfKey: SelfKey, giftYear: Int, players: Map[String, Player]): String =
    players(selfKey).giftHistory(giftYear).givee

  def playersGetGiver(selfKey: SelfKey, giftYear: Int, players: Map[String, Player]): String =
    players(selfKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(playerKey: PlayerKey, giftYear: Int, giftPair: GiftPair, players: Map[String, Player]): Map[String, Player] = {
    val ngh = giftHistoryUpdateGiftHistory(giftYear)(giftPair)(players(playerKey).giftHistory)
    val nplr = playerUpdateGiftHistory(ngh)(players(playerKey))
    playersUpdatePlayer(playerKey, nplr, players)
  }

  def playersUpdateGivee(selfKey: String, giftYear: Int, givee: String, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngp = GiftPair.giftPairUpdateGivee(givee)(players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey, giftYear, ngp, players)
  }

  def playersUpdateGiver(selfKey: String, giftYear: Int, giver: String, players: Map[String, Player]): Map[String, Player] = {
    val ngp = GiftPair.giftPairUpdateGiver(giver)(players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey, giftYear, ngp, players)
  }

  def playersJsonStringToPlayers(plrsJsonString: JsonString): Either[Error, Map[String, Player]] =
    decode[Map[String, Player]](plrsJsonString)
}
