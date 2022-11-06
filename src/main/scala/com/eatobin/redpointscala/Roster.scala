package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._

case class Roster(rosterName: String, rosterYear: Int, players: Map[String, Player])

object Roster {
  type ErrorString = String

  def rosterJsonStringToRoster(s: Either[ErrorString, JsonString]): Either[ErrorString, Roster] = {
    s match {
      case Right(rFile) =>
        val res: Either[Error, Roster] = decode[Roster](rFile)
        res match {
          case Right(rDecode) => Right(rDecode)
          case Left(lDecode) => Left(lDecode.toString)
        }
      case Left(lFile) =>
        Left(lFile)
    }
  }
}
