package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.{GiftYear, giftHistoryAddYear, giftHistoryUpdateGiftHistory}
import com.eatobin.redpointscala.GiftPair._
import com.eatobin.redpointscala.Player.{PlayerName, playerUpdateGiftHistory}

object Players {
  type Players = Map[PlayerKey, Player]

  def playersUpdatePlayer(playerKey: PlayerKey)(player: Player)(players: Players): Players =
    players.updated(playerKey, player)

  def playersGetPlayerName(playerKey: PlayerKey)(players: Players): PlayerName =
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

  def playersGetMyGivee(selfKey: PlayerKey)(giftYear: GiftYear)(players: Players): Givee =
    players(selfKey).giftHistory(giftYear).givee

  def playersGetMyGiver(selfKey: PlayerKey)(giftYear: GiftYear)(players: Players): Giver =
    players(selfKey).giftHistory(giftYear).giver

  private def playersSetGiftPair(playerKey: PlayerKey)(giftYear: GiftYear)(giftPair: GiftPair)(players: Players): Players = {
    val ngh = giftHistoryUpdateGiftHistory(giftYear)(giftPair)(players(playerKey).giftHistory)
    val nplr = playerUpdateGiftHistory(ngh)(players(playerKey))
    playersUpdatePlayer(playerKey)(nplr)(players)
  }

  def playersUpdateMyGivee(selfKey: PlayerKey)(giftYear: GiftYear)(givee: Givee)(players: Players): Players = {
    val ngp = giftPairUpdateGivee(givee)(players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey)(giftYear)(ngp)(players)
  }

  def playersUpdateMyGiver(selfKey: PlayerKey)(giftYear: GiftYear)(giver: Giver)(players: Players): Players = {
    val ngp = giftPairUpdateGiver(giver)(players(selfKey).giftHistory(giftYear))
    playersSetGiftPair(selfKey)(giftYear)(ngp)(players)
  }
}
