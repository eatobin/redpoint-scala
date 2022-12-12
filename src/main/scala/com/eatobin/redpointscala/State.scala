package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftHistory.GiftYear
import com.eatobin.redpointscala.GiftPair.{Givee, Giver}
import com.eatobin.redpointscala.Hat.Hat
import com.eatobin.redpointscala.Players.Players
import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}

case class State(rosterName: RosterName, rosterYear: RosterYear, players: Players, giftYear: GiftYear, giveeHat: Hat, giverHat: Hat, maybeGivee: Option[Givee], maybeGiver: Option[Giver], discards: Hat)
//  private val roster: Roster = Roster("The Beatles", 2014, players, 0, emptyHat, emptyHat, None, None, emptyHat)
