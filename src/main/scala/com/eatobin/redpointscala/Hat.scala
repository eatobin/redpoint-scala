package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{Givee, PlayerKey}
import com.eatobin.redpointscala.Players.Players

object Hat {
  type Hat = Set[String]

  def hatMakeHat(players: Players): Hat =
    players.keySet

  def hatRemovePuck(playerKey: PlayerKey, hat: Hat): Hat =
    hat - playerKey

  def hatDiscardGivee(givee: Givee, discards: Hat): Hat =
    discards + givee

  def hatReturnDiscards(discards: Hat, geHat: Hat): Hat =
    geHat ++ discards
}
