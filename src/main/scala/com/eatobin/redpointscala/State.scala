package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, Giver, JsonString, PlayerKey}
import com.eatobin.redpointscala.Hat.{Hat, hatDiscardGivee, hatMakeHat, hatRemovePuck, hatReturnDiscards}
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
                  discards: Hat,
                  continue: Quit
                )

object State {
  type Quit = String

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
      discards = Set(),
      continue = state.continue
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
      discards = Set(),
      continue = state.continue
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
      discards = state.discards,
      continue = state.continue
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
      discards = hatDiscardGivee(givee, state.discards),
      continue = state.continue
    )
    newState
  }

  @tailrec
  def stateGiveeIsSuccessOrFailure(state: State): State = {
    if (state.maybeGivee.isEmpty) {
      state
    } else {
      if (rulesGiveeNotSelf(state.maybeGiver.get, state.maybeGivee.get) &&
        rulesGiveeNotRecip(state.maybeGiver.get, state.maybeGivee.get, state.giftYear, state.players) &&
        rulesGiveeNotRepeat(state.maybeGiver.get, state.maybeGivee.get, state.giftYear, state.players)) {
        stateGiveeIsSuccess(state)
      } else {
        stateGiveeIsSuccessOrFailure(stateGiveeIsFailure(state))
      }
    }
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

  private def statePrintResults(state: State): Unit = {
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

  def statePrintAndAsk(state: State): State = {
    statePrintStringGivingRoster(state)
    println()
    val reply: String = readLine("Continue? ('q' to quit): ")
    state.copy(continue = reply)
  }

  def stateJsonStringToState(jsonString: JsonString): Either[Error, State] =
    decode[State](jsonString)

  private def stateIncrement(state: State): State = state.copy(rosterYear = state.rosterYear + 1)

  private def stateDecrement(state: State): State = state.copy(rosterYear = state.rosterYear - 2000)

  @tailrec
  def stateUpTo2020(state: State): State = {
    state.rosterYear match {
      case 2020 => state
      case _ => stateUpTo2020(stateIncrement(state))
    }
  }

  @tailrec
  private def stateDownTo20(state: State): State = {
    state.rosterYear match {
      case 20 => state
      case _ => stateDownTo20(stateDecrement(state))
    }
  }

  @tailrec
  def stateTo20(state: State): State = {
    if (state.rosterYear != 20) {
      if (state.rosterYear != 2020) {
        stateTo20(stateUpTo2020(state))
      } else {
        stateTo20(stateDownTo20(state))
      }
    } else {
      state
    }
  }
}
