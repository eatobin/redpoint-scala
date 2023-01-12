package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{Givee, JsonString, PlayerKey}
import com.eatobin.redpointscala.Players.Players
import io.circe.Error
import io.circe.parser._

object Hat {
  type Hat = Set[PlayerKey]
  type Discards = Set[PlayerKey]

  def hatMakeHat(players: Players): Hat =
    players.keySet

  def hatRemovePuck(playerKey: PlayerKey, hat: Hat): Hat =
    hat - playerKey

  def hatDiscardGivee(givee: Givee, discards: Discards): Hat =
    discards + givee

  def hatReturnDiscards(discards: Discards, geHat: Hat): Hat =
    geHat ++ discards

  def hatJsonStringToHat(jsonString: JsonString): Either[Error, Hat] =
    decode[Hat](jsonString)
}
