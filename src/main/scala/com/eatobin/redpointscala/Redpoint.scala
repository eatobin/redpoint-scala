package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.{Givee, Giver, JsonString, PlayerKey}
import com.eatobin.redpointscala.Hat.{Hat, hatDiscardGivee, hatMakeHat, hatRemovePuck, hatReturnDiscards}
import com.eatobin.redpointscala.Players._
import com.eatobin.redpointscala.Roster.{ErrorString, RosterName, RosterYear}
import com.eatobin.redpointscala.Rules.{rulesGiveeNotRecip, rulesGiveeNotRepeat, rulesGiveeNotSelf}

import scala.io.Source._
import scala.io.StdIn.readLine

object Redpoint {
  type FilePath = String
  var aGiftYear: Int = 0
  var aMaybeGiver: Option[Giver] = None
  var aMaybeGivee: Option[Givee] = None
  var aPlayers: Players = Map()
  var aGiverHat: Hat = Set()
  var aGiveeHat: Hat = Set()
  var aDiscards: Hat = Set()
  var aRosterName: RosterName = ""
  var aRosterYear: RosterYear = 0
  val filePath: FilePath = "src/main/resources/blackhawks.json"

  def main(args: Array[String]): Unit = {
    redpointRosterOrQuit(filePath)
    while (redpointPrintAndAsk(aRosterName)(aRosterYear).toLowerCase != "q") {
      redpointStartNewYear()
      while (aMaybeGiver.isDefined) {
        while (aMaybeGivee.isDefined) {
          if (rulesGiveeNotSelf(aMaybeGiver.get, aMaybeGivee.get) &&
            rulesGiveeNotRecip(aMaybeGiver.get, aMaybeGivee.get, aGiftYear, aPlayers) &&
            rulesGiveeNotRepeat(aMaybeGiver.get, aMaybeGivee.get, aGiftYear, aPlayers)) {
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

  def redpointReadFileIntoJsonString(filepath: FilePath): Either[ErrorString, JsonString] =
    try {
      val bufferedSource = fromFile(filepath)
      val js = bufferedSource.getLines().mkString
      bufferedSource.close
      Right(js)
    } catch {
      case e: Exception =>
        Left(e.getMessage)
    }

  def redpointRosterOrQuit(filepath: FilePath): Unit = {
    val rosterStringEither = redpointReadFileIntoJsonString(filepath)
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

  private def redpointRandom(hat: Hat): PlayerKey = {
    val n: Int = util.Random.nextInt(hat.size)
    hat.iterator.drop(n).next()
  }

  def redpointDrawPuck(hat: Hat): Option[PlayerKey] = {
    if (hat.nonEmpty) {
      Some(redpointRandom(hat))
    } else {
      None
    }
  }

  def redpointStartNewYear(): Unit = {
    aGiftYear = aGiftYear + 1
    aPlayers = playersAddYear(aPlayers)
    aGiverHat = hatMakeHat(aPlayers)
    aGiveeHat = hatMakeHat(aPlayers)
    aMaybeGiver = redpointDrawPuck(aGiverHat)
    aMaybeGivee = redpointDrawPuck(aGiveeHat)
    aDiscards = Set()
  }

  def redpointSelectNewGiver(): Unit = {
    val giver: Giver = aMaybeGiver.get
    aGiverHat = hatRemovePuck(giver, aGiverHat)
    aGiveeHat = hatReturnDiscards(aDiscards, aGiveeHat)
    aDiscards = Set()
    aMaybeGiver = redpointDrawPuck(aGiverHat)
    aMaybeGivee = redpointDrawPuck(aGiveeHat)
  }

  def redpointGiveeIsSuccess(): Unit = {
    val giver: Giver = aMaybeGiver.get
    val givee: Givee = aMaybeGivee.get
    aPlayers = playersUpdateMyGivee(giver)(aGiftYear)(givee)(aPlayers)
    aPlayers = playersUpdateMyGiver(givee)(aGiftYear)(giver)(aPlayers)
    aGiveeHat = hatRemovePuck(givee, aGiveeHat)
    aMaybeGivee = None
  }

  def redpointGiveeIsFailure(): Unit = {
    val givee: Givee = aMaybeGivee.get
    aGiveeHat = hatRemovePuck(givee, aGiveeHat)
    aDiscards = hatDiscardGivee(givee, aDiscards)
    aMaybeGivee = redpointDrawPuck(aGiveeHat)
  }

  def redpointErrors(): Seq[PlayerKey] = {
    val playerKeys: Seq[PlayerKey] = aPlayers.keys.toSeq
    val playerErrors = {
      for {
        playerKeyMe <- playerKeys
        myGiverKey = playersGetMyGiver(playerKeyMe)(aGiftYear)(aPlayers)
        myGiveeKey = playersGetMyGivee(playerKeyMe)(aGiftYear)(aPlayers)
        if playerKeyMe == myGiverKey || playerKeyMe == myGiveeKey
      } yield playerKeyMe
    }
    playerErrors.sorted
  }

  def redpointPrintResults(): Unit = {
    val playerKeys: Seq[PlayerKey] = aPlayers.keys.toSeq.sorted
    for (playerKey <- playerKeys) yield {
      val playerName = playersGetPlayerName(playerKey)(aPlayers)
      val giveeKey = playersGetMyGivee(playerKey)(aGiftYear)(aPlayers)
      val giveeName = playersGetPlayerName(giveeKey)(aPlayers)
      val giverKey = playersGetMyGiver(playerKey)(aGiftYear)(aPlayers)

      if (playerKey == giveeKey && playerKey == giverKey) {
        println("%s is neither **buying** for nor **receiving** from anyone - **ERROR**".format(playerName))
      } else if (playerKey == giverKey) {
        println("%s is **receiving** from no one - **ERROR**".format(playerName))
      } else if (playerKey == giveeKey) {
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

  def redpointPrintStringGivingRoster(rosterName: RosterName)(rosterYear: RosterYear): Unit = {
    println()
    println("%s - Year %d Gifts:".format(rosterName, rosterYear + aGiftYear))
    println()
    redpointPrintResults()
  }

  def redpointPrintAndAsk(rosterName: RosterName)(rosterYear: RosterYear): String = {
    redpointPrintStringGivingRoster(rosterName)(rosterYear)
    println()
    readLine("Continue? ('q' to quit): ")
  }
}
