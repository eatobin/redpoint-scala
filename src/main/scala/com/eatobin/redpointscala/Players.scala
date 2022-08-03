package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

object Players {
  def updatePlayer(playerKey: JsonString, player: Player, players: Map[JsonString, Player]): Map[String, Player] =
    players.updated(playerKey, player)

  def getPlayerName(playerKey: JsonString, players: Map[JsonString, Player]): String =
    players(playerKey).playerName

  def addYear(players: Map[String, Player]): Map[String, Player] = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = GiftHistory.addYear(playerKey, gh)
      val nplr = Player.updateGiftHistory(ngh, player)
      playerKey -> nplr
    }
    nplrs
  }

  def getGivee(selfKey: JsonString, giftYear: Int, players: Map[JsonString, Player]): String =
    players(selfKey).giftHistory(giftYear).givee

  def getGiver(selfKey: JsonString, giftYear: Int, players: Map[JsonString, Player]): String =
    players(selfKey).giftHistory(giftYear).giver

  private def setGiftPair(playerKey: JsonString, giftYear: Int, giftPair: GiftPair, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngh = GiftHistory.updateGiftHistory(giftYear, giftPair, players(playerKey).giftHistory)
    val nplr = Player.updateGiftHistory(ngh, players(playerKey))
    updatePlayer(playerKey, nplr, players)
  }

  def updateGivee(selfKey: JsonString, giftYear: Int, givee: JsonString, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngp = GiftPair.updateGivee(givee, players(selfKey).giftHistory(giftYear))
    setGiftPair(selfKey, giftYear, ngp, players)
  }

  def updateGiver(selfKey: JsonString, giftYear: Int, giver: JsonString, players: Map[JsonString, Player]): Map[String, Player] = {
    val ngp = GiftPair.updateGiver(giver, players(selfKey).giftHistory(giftYear))
    setGiftPair(selfKey, giftYear, ngp, players)
  }

  def jsonStringToPlayers(plrsString: String): Either[Error, Map[String, Player]] =
    decode[Map[String, Player]](plrsString)
}
