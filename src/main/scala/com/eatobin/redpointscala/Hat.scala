package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{Givee, JsonString, PlayerKey}
import com.eatobin.redpointscala.Players.Players
import io.circe.Error
import io.circe.parser._

object Hat {
  type Hat = Set[PlayerKey]

  def hatMakeHat(players: Players): Hat =
    players.keySet

  def hatRemovePuck(playerKey: PlayerKey, hat: Hat): Hat =
    hat - playerKey

  def hatDiscardGivee(givee: Givee, discards: Hat): Hat =
    discards + givee

  def hatReturnDiscards(discards: Hat, geHat: Hat): Hat =
    geHat ++ discards

  def hatJsonStringToHat(jsonString: JsonString): Either[Error, Hat] =
    decode[Hat](jsonString)
}
