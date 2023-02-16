package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.{GiftYearTA, giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import com.eatobin.redpointscala.GiftPair._
import com.eatobin.redpointscala.Player.{PlayerNameTA, playerUpdateGiftHistory}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

import scala.collection.immutable.SortedMap

object Players {
  type Players = SortedMap[PlayerKeyTA, Player]

  def playersJsonStringToPlayers(jsonString: JsonStringTA): Either[Error, Players] =
    decode[Players](jsonString)

  def playersUpdatePlayer(playerKey: PlayerKeyTA)(player: Player)(players: Players): Players =
    players.updated(playerKey, player)

  def playersGetPlayerName(playerKey: PlayerKeyTA)(players: Players): PlayerNameTA =
    players(playerKey).playerName

  def playersAddYear(players: Players): Players = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = giftHistoryAddYear(playerKey)(gh)
      val nplr = playerUpdateGiftHistory(ngh)(player)
      playerKey -> nplr
    }
    nplrs
  }

  def playersGetMyGivee(selfKey: PlayerKeyTA)(players: Players)(giftYear: GiftYearTA): GiveeTA =
    players(selfKey).giftHistory(giftYear).givee

  def playersGetMyGiver(selfKey: PlayerKeyTA)(players: Players)(giftYear: GiftYearTA): GiverTA =
    players(selfKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(playerKey: PlayerKeyTA)(giftYear: GiftYearTA)(giftPair: GiftPair)(players: Players): Players = {
    val ngh = giftHistoryUpdateGiftHistory(giftYear)(giftPair)(players(playerKey).giftHistory)
    val nplr = playerUpdateGiftHistory(ngh)(players(playerKey))
    playersUpdatePlayer(playerKey)(nplr)(players)
  }

  def playersUpdateMyGivee(selfKey: PlayerKeyTA)(givee: GiveeTA)(giftYear: GiftYearTA)(players: Players): Players = {
    val ngp = giftPairUpdateGivee(givee)(players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey)(giftYear)(ngp)(players)
  }

  def playersUpdateMyGiver(selfKey: PlayerKeyTA)(giver: GiverTA)(giftYear: GiftYearTA)(players: Players): Players = {
    val ngp = giftPairUpdateGiver(giver)(players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey)(giftYear)(ngp)(players)
  }
}
