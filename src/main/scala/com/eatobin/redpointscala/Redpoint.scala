package com.eatobin.redpointscala

import com.eatobin.redpointscala.Hats.{hatsDiscardGivee, hatsMakeHat, hatsRemovePuck, hatsReturnDiscards}
import com.eatobin.redpointscala.Players._
import com.eatobin.redpointscala.Rules.{rulesGiveeNotRecip, rulesGiveeNotRepeat, rulesGiveeNotSelf}

import scala.io.Source._
import scala.io.StdIn.readLine

object Redpoint {
  var agYear: Int = 0
  var maybeGiver: Option[String] = None
  var maybeGivee: Option[String] = None
  var aPlayers: Map[String, Player] = Map()
  var agrHat: Set[String] = Set()
  var ageHat: Set[String] = Set()
  var aDiscards: Set[String] = Set()
  var aRosterName: String = ""
  var aRosterYear: Int = 0
  val filePath: String = "src/main/resources/blackhawks.json"

  def main(args: Array[String]): Unit = {
    redpointRosterOrQuit(filePath)
    while (redpointPrintAndAsk(aRosterName, aRosterYear).toLowerCase != "q") {
      redpointStartNewYear()
      while (maybeGiver.isDefined) {
        while (maybeGivee.isDefined) {
          if (rulesGiveeNotSelf(maybeGiver.get, maybeGivee.get) &&
            rulesGiveeNotRecip(maybeGiver.get, maybeGivee.get, agYear, aPlayers) &&
            rulesGiveeNotRepeat(maybeGiver.get, maybeGivee.get, agYear, aPlayers)) {
            redpointGiveeIsSuccess()
          } else {
            redpointGiveeIsFailure()
          }
        }
        redpointSelectNewGiver()
      }
    }
    println()
    println("This was fun!")
    println("Talk about a position with Redpoint?")
    println("Please call: Eric Tobin 773-679-6617")
    println()
  }

  def redpointReadFileIntoJsonString(fp: String): Either[ErrorString, JsonString] =
    try {
      val bufferedSource = fromFile(fp)
      val js = bufferedSource.getLines().mkString
      bufferedSource.close
      Right(js)
    } catch {
      case e: Exception =>
        Left(e.getMessage)
    }

  def redpointRosterOrQuit(fp: String): Unit = {
    val rosterStringEither = redpointReadFileIntoJsonString(fp)
    rosterStringEither match {
      case Right(rs) =>
        val rosterEither: Either[ErrorString, Roster] = Roster.rosterJsonStringToRoster(Right(rs))
        rosterEither match {
          case Right(r) =>
            aRosterName = r.rosterName
            aRosterYear = r.rosterYear
            aPlayers = r.players
          case Left(pe) =>
            println(pe)
        }
      case Left(fe) =>
        println(fe)
    }
  }

  private def redpointRandom(s: Set[String]): String = {
    val n: Int = util.Random.nextInt(s.size)
    s.iterator.drop(n).next()
  }

  def redpointDrawPuck(hat: Set[String]): Option[String] = {
    if (hat.nonEmpty) {
      Some(redpointRandom(hat))
    } else {
      None
    }
  }

  def redpointStartNewYear(): Unit = {
    agYear = agYear + 1
    aPlayers = playersAddYear(aPlayers)
    agrHat = hatsMakeHat(aPlayers)
    ageHat = hatsMakeHat(aPlayers)
    maybeGiver = redpointDrawPuck(agrHat)
    maybeGivee = redpointDrawPuck(ageHat)
    aDiscards = Set()
  }

  def redpointSelectNewGiver(): Unit = {
    val giver: String = maybeGiver.get
    agrHat = hatsRemovePuck(giver, agrHat)
    ageHat = hatsReturnDiscards(aDiscards, ageHat)
    aDiscards = Set()
    maybeGiver = redpointDrawPuck(agrHat)
    maybeGivee = redpointDrawPuck(ageHat)
  }

  def redpointGiveeIsSuccess(): Unit = {
    val giver: String = maybeGiver.get
    val givee: String = maybeGivee.get
    aPlayers = playersUpdateGivee(giver, agYear, givee, aPlayers)
    aPlayers = playersUpdateGiver(givee, agYear, giver, aPlayers)
    ageHat = hatsRemovePuck(givee, ageHat)
    maybeGivee = None
  }

  def redpointGiveeIsFailure(): Unit = {
    val givee: String = maybeGivee.get
    ageHat = hatsRemovePuck(givee, ageHat)
    aDiscards = hatsDiscardGivee(givee, aDiscards)
    maybeGivee = redpointDrawPuck(ageHat)
  }

  def redpointErrors(): Seq[String] = {
    val plrKeys: Seq[String] = aPlayers.keys.toSeq
    val plrErrors = {
      for {
        plrSym <- plrKeys
        giverCode = playersGetGiver(plrSym, agYear, aPlayers)
        giveeCode = playersGetGivee(plrSym, agYear, aPlayers)
        if plrSym == giverCode || plrSym == giveeCode
      } yield plrSym
    }
    plrErrors.sorted
  }

  def redpointPrintResults(): Unit = {
    val plrKeys: Seq[String] = aPlayers.keys.toSeq.sorted
    for (plrSym <- plrKeys) yield {
      val playerName = playersGetPlayerName(plrSym, aPlayers)
      val giveeCode = playersGetGivee(plrSym, agYear, aPlayers)
      val giveeName = playersGetPlayerName(giveeCode, aPlayers)
      val giverCode = playersGetGiver(plrSym, agYear, aPlayers)

      if (plrSym == giveeCode && plrSym == giverCode) {
        println("%s is **buying** for nor **receiving** from anyone - **ERROR**".format(playerName))
      } else if (plrSym == giverCode) {
        println("%s is **receiving** from no one - **ERROR**".format(playerName))
      } else if (plrSym == giveeCode) {
        println("%s is **buying** for no one - **ERROR**".format(playerName))
      } else {
        println("%s is buying for %s".format(playerName, giveeName))
      }
    }
    if (redpointErrors().nonEmpty) {
      println()
      println("There is a logic error in this year's pairings.")
      println("Do you see how it occurs?")
      println("If not... call me and I'll explain!")
    }
  }

  def redpointPrintStringGivingRoster(rName: String, rYear: Int): Unit = {
    println()
    println("%s - Year %d Gifts:".format(rName, rYear + agYear))
    println()
    redpointPrintResults()
  }

  def redpointPrintAndAsk(rName: String, rYear: Int): String = {
    redpointPrintStringGivingRoster(rName, rYear)
    println()
    readLine("Continue? ('q' to quit): ")
  }
}
