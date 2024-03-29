package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYearTA
import com.eatobin.redpointscala.GiftPair.{GiveeTA, GiverTA, JsonStringTA, PlayerKeyTA}
import com.eatobin.redpointscala.Hat._
import com.eatobin.redpointscala.MyState.{QuitTA, RosterNameTA, RosterYearTA}
import com.eatobin.redpointscala.Players._
import com.eatobin.redpointscala.Rules.{rulesGiveeNotRecip, rulesGiveeNotRepeat, rulesGiveeNotSelf}
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

import scala.annotation.tailrec
import scala.collection.immutable.SortedSet
import scala.io.StdIn.readLine

case class MyState(
    rosterName: RosterNameTA,
    rosterYear: RosterYearTA,
    players: PlayersTA,
    giftYear: GiftYearTA,
    giveeHat: HatTA,
    giverHat: HatTA,
    maybeGivee: Option[GiveeTA],
    maybeGiver: Option[GiverTA],
    discards: DiscardsTA,
    quit: QuitTA
)

object MyState {
  type RosterNameTA = String
  type RosterYearTA = Int
  type QuitTA = String

  def myStateJsonStringToMyState(
      jsonString: JsonStringTA
  ): Either[Error, MyState] =
    decode[MyState](jsonString)

  def myStateDrawPuck(hat: HatTA): Option[PlayerKeyTA] = {
    if (hat.isEmpty) {
      None
    } else {
      val i: Int = util.Random.nextInt(hat.size)
      Some(hat.iterator.drop(i).next())
    }
  }

  def myStateStartNewYear(state: MyState): MyState = {
    val freshHat: HatTA = hatMakeHat(state.players)
    val newState: MyState = MyState(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = playersAddYear(state.players),
      giftYear = state.giftYear + 1,
      giveeHat = freshHat,
      giverHat = freshHat,
      maybeGivee = myStateDrawPuck(freshHat),
      maybeGiver = myStateDrawPuck(freshHat),
      discards = SortedSet(),
      quit = state.quit
    )
    newState
  }

  def myStateGiveeIsFailure(state: MyState): MyState = {
    val giveeToRemove: GiveeTA = state.maybeGivee.get
    val diminishedGiveeHat: HatTA = hatRemovePuck(giveeToRemove, state.giveeHat)
    val newState: MyState = MyState(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = diminishedGiveeHat,
      giverHat = state.giverHat,
      maybeGivee = myStateDrawPuck(diminishedGiveeHat),
      maybeGiver = state.maybeGiver,
      discards = hatDiscardGivee(giveeToRemove, state.discards),
      quit = state.quit
    )
    newState
  }

  def myStateGiveeIsSuccess(state: MyState): MyState = {
    val currentGiver: GiverTA = state.maybeGiver.get
    val currentGivee: GiveeTA = state.maybeGivee.get
    val updatedGiveePlayers: PlayersTA = playersUpdateMyGivee(currentGiver)(
      currentGivee
    )(state.giftYear)(state.players)
    val newState: MyState = MyState(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = playersUpdateMyGiver(currentGivee)(currentGiver)(
        state.giftYear
      )(updatedGiveePlayers),
      giftYear = state.giftYear,
      giveeHat = hatRemovePuck(currentGivee, state.giveeHat),
      giverHat = state.giverHat,
      maybeGivee = None,
      maybeGiver = state.maybeGiver,
      discards = state.discards,
      quit = state.quit
    )
    newState
  }

  def myStateSelectNewGiver(state: MyState): MyState = {
    val giverToRemove: GiverTA = state.maybeGiver.get
    val replenishedGiveeHat: HatTA =
      hatReturnDiscards(state.discards, state.giveeHat)
    val diminishedGiverHat: HatTA = hatRemovePuck(giverToRemove, state.giverHat)
    val newState: MyState = MyState(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = replenishedGiveeHat,
      giverHat = diminishedGiverHat,
      maybeGivee = myStateDrawPuck(replenishedGiveeHat),
      maybeGiver = myStateDrawPuck(diminishedGiverHat),
      discards = SortedSet(),
      quit = state.quit
    )
    newState
  }

  def myStateErrors(state: MyState): Seq[PlayerKeyTA] = {
    val playerKeys: Seq[PlayerKeyTA] = state.players.keys.toSeq
    val playerErrors = {
      for {
        playerKeyMe: PlayerKeyTA <- playerKeys
        myGiverKey: GiverTA = playersGetMyGiver(playerKeyMe)(state.players)(
          state.giftYear
        )
        myGiveeKey: GiveeTA = playersGetMyGivee(playerKeyMe)(state.players)(
          state.giftYear
        )
        if playerKeyMe == myGiverKey || playerKeyMe == myGiveeKey
      } yield playerKeyMe
    }
    playerErrors.sorted
  }

  def myStatePrintResults(state: MyState): MyState = {
    println()
    println(
      "%s - Year %d Gifts:".format(
        state.rosterName,
        state.rosterYear + state.giftYear
      )
    )
    println()

    val playerKeys: Seq[PlayerKeyTA] = state.players.keys.toSeq.sorted
    for (playerKey <- playerKeys) yield {
      val playerName = playersGetPlayerName(playerKey)(state.players)
      val giveeKey = playersGetMyGivee(playerKey)(state.players)(state.giftYear)
      val giveeName = playersGetPlayerName(giveeKey)(state.players)
      val giverKey = playersGetMyGiver(playerKey)(state.players)(state.giftYear)

      if (playerKey == giveeKey && playerKey == giverKey) {
        println(
          "%s is neither **buying** for nor **receiving** from anyone - **ERROR**"
            .format(playerName)
        )
      } else if (playerKey == giverKey) {
        println(
          "%s is **receiving** from no one - **ERROR**".format(playerName)
        )
      } else if (playerKey == giveeKey) {
        println("%s is **buying** for no one - **ERROR**".format(playerName))
      } else {
        println("%s is buying for %s".format(playerName, giveeName))
      }
    }
    if (myStateErrors(state).nonEmpty) {
      println()
      println("There is a logic error in this year's pairings.")
      println("Do you see how it occurs?")
      println("If not... call me and I'll explain!")
    }
    state
  }

  def myStateAskContinue(state: MyState): MyState = {
    println()
    val reply: String = readLine("Continue? ('q' to quit): ")
    state.copy(quit = reply)
  }

  def myStateUpdateAndRunNewYear(state: MyState): MyState = {
    val newYearState: MyState = myStateStartNewYear(state)
    myStateLoop(newYearState)
  }

  @tailrec
  private def myStateLoop(alteredState: MyState): MyState = {
    if (alteredState.maybeGiver.isDefined) {
      if (alteredState.maybeGivee.isDefined) {
        if (
          rulesGiveeNotSelf(
            alteredState.maybeGiver.get,
            alteredState.maybeGivee.get
          ) &&
          rulesGiveeNotRecip(
            alteredState.maybeGiver.get,
            alteredState.maybeGivee.get,
            alteredState.giftYear,
            alteredState.players
          ) &&
          rulesGiveeNotRepeat(
            alteredState.maybeGiver.get,
            alteredState.maybeGivee.get,
            alteredState.giftYear,
            alteredState.players
          )
        ) {
          myStateLoop(myStateGiveeIsSuccess(alteredState))
        } else {
          myStateLoop(myStateGiveeIsFailure(alteredState))
        }
      } else {
        myStateLoop(myStateSelectNewGiver(alteredState))
      }
    } else {
      alteredState
    }
  }
}
