package com.eatobin.redpointscala

object Hats {
  def makeHat(players: Map[String, Player]): Set[String] =
    players.keySet

  def removePuck(playerKey: JsonString, hat: Set[JsonString]): Set[String] =
    hat - playerKey

  def discardGivee(givee: JsonString, discards: Set[JsonString]): Set[String] =
    discards + givee

  def returnDiscards(discards: Set[JsonString], geHat: Set[JsonString]): Set[String] =
    geHat ++ discards
}
