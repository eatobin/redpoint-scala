//package com.eatobin.redpointscala
//
//import com.eatobin.redpointscala.GiftHistory.GiftYear
//import com.eatobin.redpointscala.GiftPair.{Givee, Giver, JsonString}
//import com.eatobin.redpointscala.Hat.Hat
//import com.eatobin.redpointscala.Players.Players
//import com.eatobin.redpointscala.Roster.{RosterName, RosterYear}
//import io.circe.Error
//import io.circe.generic.auto._
//import io.circe.parser._
//import io.circe.syntax._
//
//case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players, giftYear: GiftYear, giveeHat: Hat, giverHat: Hat, maybeGivee: Option[Givee], maybeGiver: Option[Giver], discards: Hat)
//
//object Roster {
//  type RosterName = String
//  type RosterYear = Int
//  type ErrorString = String
//
//  def rosterJsonStringToRoster(s: Either[ErrorString, JsonString]): Either[ErrorString, Roster] = {
//    s match {
//      case Right(rFile) =>
//        val res: Either[Error, Roster] = decode[Roster](rFile)
//        res match {
//          case Right(rDecode) => Right(rDecode)
//          case Left(lDecode) => Left(lDecode.toString)
//        }
//      case Left(lFile) =>
//        Left(lFile)
//    }
//  }
//
//  def rosterRosterToJsonString(roster: Roster): JsonString =
//    roster.asJson.noSpaces
//}
