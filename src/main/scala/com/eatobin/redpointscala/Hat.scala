package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{GiveeTA, JsonString, PlayerKeyTA}
import com.eatobin.redpointscala.Players.Players
import io.circe.Error
import io.circe.parser._

import scala.collection.immutable.SortedSet

object Hat {
  type Hat = SortedSet[PlayerKeyTA]
  type Discards = Hat

  def hatJsonStringToHat(jsonString: JsonString): Either[Error, Hat] =
    decode[Hat](jsonString)

  def hatMakeHat(players: Players): Hat =
    players.keySet

  def hatRemovePuck(playerKey: PlayerKeyTA, hat: Hat): Hat =
    hat - playerKey

  def hatDiscardGivee(givee: GiveeTA, discards: Discards): Hat =
    discards + givee

  def hatReturnDiscards(discards: Discards, geHat: Hat): Hat =
    geHat ++ discards
}
