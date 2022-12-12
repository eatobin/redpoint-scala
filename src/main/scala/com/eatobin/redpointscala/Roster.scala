package com.eatobin.redpointscala

import com.eatobin.redpointscala.Players.Players
import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster {
  type RosterName = String
  type RosterYear = Int
}
