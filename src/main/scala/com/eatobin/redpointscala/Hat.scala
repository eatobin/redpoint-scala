package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{GiveeTA, JsonStringTA, PlayerKeyTA}
import com.eatobin.redpointscala.Players.PlayersTA
import io.circe.Error
import io.circe.parser._

import scala.collection.immutable.SortedSet

object Hat {
  type HatTA = SortedSet[PlayerKeyTA]
  type DiscardsTA = HatTA

  def hatJsonStringToHat(jsonString: JsonStringTA): Either[Error, HatTA] =
    decode[HatTA](jsonString)

  def hatMakeHat(players: PlayersTA): HatTA =
    players.keySet

  def hatRemovePuck(playerKey: PlayerKeyTA, hat: HatTA): HatTA =
    hat - playerKey

  def hatDiscardGivee(givee: GiveeTA, discards: DiscardsTA): HatTA =
    discards + givee

  def hatReturnDiscards(discards: DiscardsTA, geHat: HatTA): HatTA =
    geHat ++ discards
}
