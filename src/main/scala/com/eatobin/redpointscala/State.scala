package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, Giver, PlayerKey}
import com.eatobin.redpointscala.Hat.{Hat, hatDiscardGivee, hatMakeHat, hatRemovePuck, hatReturnDiscards}
import com.eatobin.redpointscala.Players.{Players, playersAddYear, playersGetMyGivee, playersGetMyGiver, playersGetPlayerName, playersUpdateMyGivee, playersUpdateMyGiver}
import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}

import scala.io.StdIn.readLine

case class State(rosterName: RosterName, rosterYear: RosterYear, players: Players, giftYear: GiftYear, giveeHat: Hat, giverHat: Hat, maybeGivee: Option[Givee], maybeGiver: Option[Giver], discards: Hat)

object State {
  private def stateRandom(hat: Hat): PlayerKey = {
    val n: Int = util.Random.nextInt(hat.size)
    hat.iterator.drop(n).next()
  }

  def stateDrawPuck(hat: Hat): Option[PlayerKey] = {
    if (hat.nonEmpty) {
      Some(stateRandom(hat))
    } else {
      None
    }
  }

  def stateStartNewYear(state: State): State = {
    val newPlayers: Players = playersAddYear(state.players)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = newPlayers,
      giftYear = state.giftYear + 1,
      giveeHat = hatMakeHat(newPlayers),
      giverHat = hatMakeHat(newPlayers),
      maybeGivee = stateDrawPuck(hatMakeHat(newPlayers)),
      maybeGiver = stateDrawPuck(hatMakeHat(newPlayers)),
      discards = Set()
    )
    newState
  }

  def stateSelectNewGiver(state: State): State = {
    val giver: Giver = state.maybeGiver.get
    val newGiveeHat: Hat = hatReturnDiscards(state.discards, state.giveeHat)
    val newGiverHat: Hat = hatRemovePuck(giver, state.giverHat)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = newGiveeHat,
      giverHat = newGiverHat,
      maybeGivee = stateDrawPuck(newGiveeHat),
      maybeGiver = stateDrawPuck(newGiverHat),
      discards = Set()
    )
    newState
  }

  def stateGiveeIsSuccess(state: State): State = {
    val giver: Giver = state.maybeGiver.get
    val givee: Givee = state.maybeGivee.get
    val tempPlayers = playersUpdateMyGivee(giver)(state.giftYear)(givee)(state.players)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = playersUpdateMyGiver(givee)(state.giftYear)(giver)(tempPlayers),
      giftYear = state.giftYear,
      giveeHat = hatRemovePuck(givee, state.giveeHat),
      giverHat = state.giverHat,
      maybeGivee = None,
      maybeGiver = state.maybeGiver,
      discards = state.discards
    )
    newState
  }

  def stateGiveeIsFailure(state: State): State = {
    val givee: Givee = state.maybeGivee.get
    val newGiveeHat: Hat = hatRemovePuck(givee, state.giveeHat)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = newGiveeHat,
      giverHat = state.giverHat,
      maybeGivee = stateDrawPuck(newGiveeHat),
      maybeGiver = state.maybeGiver,
      discards = hatDiscardGivee(givee, state.discards)
    )
    newState
  }

  def stateErrors(state: State): Seq[PlayerKey] = {
    val playerKeys: Seq[PlayerKey] = state.players.keys.toSeq
    val playerErrors = {
      for {
        playerKeyMe: PlayerKey <- playerKeys
        myGiverKey: Giver = playersGetMyGiver(playerKeyMe)(state.giftYear)(state.players)
        myGiveeKey: Givee = playersGetMyGivee(playerKeyMe)(state.giftYear)(state.players)
        if playerKeyMe == myGiverKey || playerKeyMe == myGiveeKey
      } yield playerKeyMe
    }
    playerErrors.sorted
  }

  def statePrintResults(state: State): Unit = {
    val playerKeys: Seq[PlayerKey] = state.players.keys.toSeq.sorted
    for (playerKey <- playerKeys) yield {
      val playerName = playersGetPlayerName(playerKey)(state.players)
      val giveeKey = playersGetMyGivee(playerKey)(state.giftYear)(state.players)
      val giveeName = playersGetPlayerName(giveeKey)(state.players)
      val giverKey = playersGetMyGiver(playerKey)(state.giftYear)(state.players)

      if (playerKey == giveeKey && playerKey == giverKey) {
        println("%s is neither **buying** for nor **receiving** from anyone - **ERROR**".format(playerName))
      } else if (playerKey == giverKey) {
        println("%s is **receiving** from no one - **ERROR**".format(playerName))
      } else if (playerKey == giveeKey) {
        println("%s is **buying** for no one - **ERROR**".format(playerName))
      } else {
        println("%s is buying for %s".format(playerName, giveeName))
      }
    }
    if (stateErrors(state).nonEmpty) {
      println()
      println("There is a logic error in this year's pairings.")
      println("Do you see how it occurs?")
      println("If not... call me and I'll explain!")
    }
  }

  def statePrintStringGivingRoster(state: State): Unit = {
    println()
    println("%s - Year %d Gifts:".format(state.rosterName, state.rosterYear + state.giftYear))
    println()
    statePrintResults(state)
  }

  def statePrintAndAsk(state: State): String = {
    statePrintStringGivingRoster(state)
    println()
    readLine("Continue? ('q' to quit): ")
  }
}
