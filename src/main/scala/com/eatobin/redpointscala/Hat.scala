package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{GiveeTA, JsonStringTA, PlayerKeyTA}
import com.eatobin.redpointscala.Players.PlayersTA
import io.circe.Error
import io.circe.parser._

import scala.collection.immutable.SortedSet

object Hat {
  type HatTA = SortedSet[PlayerKeyTA]
  type Discards = HatTA

  def hatJsonStringToHat(jsonString: JsonStringTA): Either[Error, HatTA] =
    decode[HatTA](jsonString)

  def hatMakeHat(players: PlayersTA): HatTA =
    players.keySet

  def hatRemovePuck(playerKey: PlayerKeyTA, hat: HatTA): HatTA =
    hat - playerKey

  def hatDiscardGivee(givee: GiveeTA, discards: Discards): HatTA =
    discards + givee

  def hatReturnDiscards(discards: Discards, geHat: HatTA): HatTA =
    geHat ++ discards
}
