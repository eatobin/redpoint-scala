package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, Giver, PlayerKey}
import com.eatobin.redpointscala.Hat.{Hat, hatMakeHat, hatRemovePuck, hatReturnDiscards}
import com.eatobin.redpointscala.Players.{Players, playersAddYear, playersUpdateMyGivee, playersUpdateMyGiver}
import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}

case class State(rosterName: RosterName, rosterYear: RosterYear, players: Players, giftYear: GiftYear, giveeHat: Hat, giverHat: Hat, maybeGivee: Option[Givee], maybeGiver: Option[Giver], discards: Hat)

object State {
  private def stateRandom(hat: Hat): PlayerKey = {
    val n: Int = util.Random.nextInt(hat.size)
    hat.iterator.drop(n).next()
  }

  private def stateDrawPuck(hat: Hat): Option[PlayerKey] = {
    if (hat.nonEmpty) {
      Some(stateRandom(hat))
    } else {
      None
    }
  }

  def stateStartNewYear(state: State): State = {
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = playersAddYear(state.players),
      giftYear = state.giftYear + 1,
      giveeHat = hatMakeHat(state.players),
      giverHat = hatMakeHat(state.players),
      maybeGivee = stateDrawPuck(hatMakeHat(state.players)),
      maybeGiver = stateDrawPuck(hatMakeHat(state.players)),
      discards = Set()
    )
    newState
  }

  def stateSelectNewGiver(state: State): State = {
    val giver: Giver = state.maybeGiver.get
    val newState: State = State(
      rosterName = state.rosterName,
      rosterYear = state.rosterYear,
      players = state.players,
      giftYear = state.giftYear,
      giveeHat = hatReturnDiscards(state.discards, state.giveeHat),
      giverHat = hatRemovePuck(giver, state.giverHat),
      maybeGivee = stateDrawPuck(hatMakeHat(state.players)),
      maybeGiver = stateDrawPuck(hatMakeHat(state.players)),
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
}
