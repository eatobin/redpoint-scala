package com.eatobin.redpointscala

object Hats {
  def hatsMakeHat(players: Map[String, Player]): Set[String] =
    players.keySet

  def hatsRemovePuck(playerKey: String, hat: Set[String]): Set[String] =
    hat - playerKey

  def hatsDiscardGivee(givee: String, discards: Set[String]): Set[String] =
    discards + givee

  def hatsReturnDiscards(discards: Set[String], geHat: Set[String]): Set[String] =
    geHat ++ discards
}
