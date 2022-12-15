package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, Giver, PlayerKey}
import com.eatobin.redpointscala.Hat.{Hat, hatDiscardGivee, hatMakeHat, hatRemovePuck, hatReturnDiscards}
import com.eatobin.redpointscala.Players.{Players, playersAddYear, playersUpdateMyGivee, playersUpdateMyGiver}
import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}
import com.eatobin.redpointscala.State.stateDrawPuck

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
}
