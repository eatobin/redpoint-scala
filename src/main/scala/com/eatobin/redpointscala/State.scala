package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, Giver, JsonString, PlayerKey}
import com.eatobin.redpointscala.Hat.{Discards, Hat, hatDiscardGivee, hatMakeHat, hatRemovePuck, hatReturnDiscards}
import com.eatobin.redpointscala.Players.{Players, playersAddYear, playersGetMyGivee, playersGetMyGiver, playersGetPlayerName, playersUpdateMyGivee, playersUpdateMyGiver}
import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}
import com.eatobin.redpointscala.Rules.{rulesGiveeNotRecip, rulesGiveeNotRepeat, rulesGiveeNotSelf}
import com.eatobin.redpointscala.State.Quit
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

import scala.annotation.tailrec
import scala.io.StdIn.readLine

case class State(
                  rosterName: RosterName,
                  rosterYear: RosterYear,
                  players: Players,
                  giftYear: GiftYear,
                  giveeHat: Hat,
                  giverHat: Hat,
                  maybeGivee: Option[Givee],
                  maybeGiver: Option[Giver],
                  discards: Discards,
                  quit: Quit
                )

object State {
  type Quit = String

  def stateDrawPuck(hat: Hat): Option[PlayerKey] = {
    if (hat.isEmpty) {
      None
    } else {
      val i: Int = util.Random.nextInt(hat.size)
      Some(hat.iterator.drop(i).next())
    }
  }

  def stateStartNewYear(state: State): State = {
    val freshHat: Hat = hatMakeHat(state.players)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = playersAddYear(state.players),
      giftYear = state.giftYear + 1,
      giveeHat = freshHat,
      giverHat = freshHat,
      maybeGivee = stateDrawPuck(freshHat),
      maybeGiver = stateDrawPuck(freshHat),
      discards = Set(),
      quit = state.quit
    )
    newState
  }

  def stateSelectNewGiver(state: State): State = {
    val giverToRemove: Giver = state.maybeGiver.get
    val replenishedGiveeHat: Hat = hatReturnDiscards(state.discards, state.giveeHat)
    val diminishedGiverHat: Hat = hatRemovePuck(giverToRemove, state.giverHat)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = replenishedGiveeHat,
      giverHat = diminishedGiverHat,
      maybeGivee = stateDrawPuck(replenishedGiveeHat),
      maybeGiver = stateDrawPuck(diminishedGiverHat),
      discards = Set(),
      quit = state.quit
    )
    newState
  }

  def stateGiveeIsSuccess(state: State): State = {
    val giver: Giver = state.maybeGiver.get
    val givee: Givee = state.maybeGivee.get
    val updatedGiveePlayers: Players = playersUpdateMyGivee(giver)(givee)(state.giftYear)(state.players)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = playersUpdateMyGiver(givee)(giver)(state.giftYear)(updatedGiveePlayers),
      giftYear = state.giftYear,
      giveeHat = hatRemovePuck(givee, state.giveeHat),
      giverHat = state.giverHat,
      maybeGivee = None,
      maybeGiver = state.maybeGiver,
      discards = state.discards,
      quit = state.quit
    )
    newState
  }

  def stateGiveeIsFailure(state: State): State = {
    val givee: Givee = state.maybeGivee.get
    val diminishedGiveeHat: Hat = hatRemovePuck(givee, state.giveeHat)
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = diminishedGiveeHat,
      giverHat = state.giverHat,
      maybeGivee = stateDrawPuck(diminishedGiveeHat),
      maybeGiver = state.maybeGiver,
      discards = hatDiscardGivee(givee, state.discards),
      quit = state.quit
    )
    newState
  }

  def stateUpdateAndRunNewYear(state: State): State = {
    val newYearState: State = stateStartNewYear(state)
    stateLoop(newYearState)
  }

  @tailrec
  private def stateLoop(alteredState: State): State = {
    if (alteredState.maybeGiver.isDefined) {
      if (alteredState.maybeGivee.isDefined) {
        if (rulesGiveeNotSelf(alteredState.maybeGiver.get, alteredState.maybeGivee.get) &&
          rulesGiveeNotRecip(alteredState.maybeGiver.get, alteredState.maybeGivee.get, alteredState.giftYear, alteredState.players) &&
          rulesGiveeNotRepeat(alteredState.maybeGiver.get, alteredState.maybeGivee.get, alteredState.giftYear, alteredState.players)) {
          stateLoop(stateGiveeIsSuccess(alteredState))
        } else {
          stateLoop(stateGiveeIsFailure(alteredState))
        }
      } else {
        stateLoop(stateSelectNewGiver(alteredState))
      }
    } else {
      alteredState
    }
  }

  def stateErrors(state: State): Seq[PlayerKey] = {
    val playerKeys: Seq[PlayerKey] = state.players.keys.toSeq
    val playerErrors = {
      for {
        playerKeyMe: PlayerKey <- playerKeys
        myGiverKey: Giver = playersGetMyGiver(playerKeyMe)(state.players)(state.giftYear)
        myGiveeKey: Givee = playersGetMyGivee(playerKeyMe)(state.players)(state.giftYear)
        if playerKeyMe == myGiverKey || playerKeyMe == myGiveeKey
      } yield playerKeyMe
    }
    playerErrors.sorted
  }

  def statePrintResults(state: State): State = {
    println(stateErrors(state))
    println(state)
    println()
    println("%s - Year %d Gifts:".format(state.rosterName, state.rosterYear + state.giftYear))
    println()

    val playerKeys: Seq[PlayerKey] = state.players.keys.toSeq.sorted
    for (playerKey <- playerKeys) yield {
      val playerName = playersGetPlayerName(playerKey)(state.players)
      val giveeKey = playersGetMyGivee(playerKey)(state.players)(state.giftYear)
      val giveeName = playersGetPlayerName(giveeKey)(state.players)
      val giverKey = playersGetMyGiver(playerKey)(state.players)(state.giftYear)

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
    state
  }

  def stateAskContinue(state: State): State = {
    println()
    val reply: String = readLine("Continue? ('q' to quit): ")
    state.copy(quit = reply)
  }

  def stateJsonStringToState(jsonString: JsonString): Either[Error, State] =
    decode[State](jsonString)
}
