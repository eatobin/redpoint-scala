//package com.eatobin.redpointscala
//
//import com.eatobin.redpointscala.GiftPair.{Givee, Giver, JsonString}
//import com.eatobin.redpointscala.Hat.Hat
//import com.eatobin.redpointscala.Players._
//import com.eatobin.redpointscala.Roster.{ErrorString, RosterName, RosterYear}
//
//import scala.io.Source._
//
//object Helpers {
//  type FilePath = String
//  var aGiftYear: Int = 0
//  var aMaybeGiver: Option[Giver] = None
//  var aMaybeGivee: Option[Givee] = None
//  var aPlayers: Players = Map()
//  var aGiverHat: Hat = Set()
//  var aGiveeHat: Hat = Set()
//  var aDiscards: Hat = Set()
//  var aRosterName: RosterName = ""
//  var aRosterYear: RosterYear = 0
//  val filePath: FilePath = "src/main/resources/blackhawks.json"
//
//  def helpersReadFileIntoJsonString(filepath: FilePath): Either[ErrorString, JsonString] =
//    try {
//      val bufferedSource = fromFile(filepath)
//      val jsonString = bufferedSource.getLines().mkString
//      bufferedSource.close
//      Right(jsonString)
//    } catch {
//      case e: Exception =>
//        Left(e.getMessage)
//    }
//
//  //  def helpersRosterOrQuit(filepath: FilePath): Unit = {
//  //    val rosterStringEither = helpersReadFileIntoJsonString(filepath)
//  //    rosterStringEither match {
//  //      case Right(rs) =>
//  //        val rosterEither: Either[ErrorString, Roster] = Roster.rosterJsonStringToRoster(Right(rs))
//  //        rosterEither match {
//  //          case Right(r) =>
//  //            aRosterName = r.rosterName
//  //            aRosterYear = r.rosterYear
//  //            aPlayers = r.players
//  //          case Left(pe) =>
//  //            println(pe)
//  //        }
//  //      case Left(fe) =>
//  //        println(fe)
//  //    }
//  //  }




//  //
//  //  def helpersPrintStringGivingRoster(rosterName: RosterName)(rosterYear: RosterYear): Unit = {
//  //    println()
//  //    println("%s - Year %d Gifts:".format(rosterName, rosterYear + aGiftYear))
//  //    println()
//  //    helpersPrintResults()
//  //  }
//  //
//  //  def helpersPrintAndAsk(rosterName: RosterName)(rosterYear: RosterYear): String = {
//  //    helpersPrintStringGivingRoster(rosterName)(rosterYear)
//  //    println()
//  //    readLine("Continue? ('q' to quit): ")
//  //  }
//}
