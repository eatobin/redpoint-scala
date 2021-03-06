package redpoint

import scala.io.Source
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
    rosterOrQuit(filePath)
    while (printAndAsk(aRosterName)(aRosterYear).toLowerCase != "q") {
      startNewYear()
      while (maybeGiver.isDefined) {
        while (maybeGivee.isDefined) {
          if (Rules.giveeNotSelf(maybeGiver.get, maybeGivee.get) &&
            Rules.giveeNotRecip(maybeGiver.get, maybeGivee.get, agYear, aPlayers) &&
            Rules.giveeNotRepeat(maybeGiver.get, maybeGivee.get, agYear, aPlayers)) {
            giveeIsSuccess()
          } else {
            giveeIsFailure()
          }
        }
        selectNewGiver()
      }
    }
    println()
    println("This was fun!")
    println("Talk about a position with Redpoint?")
    println("Please call: Eric Tobin 773-679-6617")
    println()
  }

  def readFileIntoJsonString(fp: String): Either[ErrorString, JsonString] =
    try {
      val bufferedSource = Source.fromFile(fp)
      val js = bufferedSource.getLines().mkString
      bufferedSource.close
      Right(js)
    } catch {
      case e: Exception =>
        Left(e.getMessage)
    }

  def rosterOrQuit(fp: String): Unit = {
    val rosterStringEither = readFileIntoJsonString(fp)
    rosterStringEither match {
      case Right(rs) =>
        val rosterEither: Either[ErrorString, Roster] = Roster.jsonStringToRoster(Right(rs))
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

  private def random(s: Set[String]): String = {
    val n = util.Random.nextInt(s.size)
    s.iterator.drop(n).next()
  }

  def drawPuck(hat: Set[String]): Option[String] = {
    if (hat.nonEmpty) {
      Some(random(hat))
    } else {
      None
    }
  }

  def startNewYear(): Unit = {
    agYear = agYear + 1
    aPlayers = Players.addYear(aPlayers)
    agrHat = Hats.makeHat(aPlayers)
    ageHat = Hats.makeHat(aPlayers)
    maybeGiver = drawPuck(agrHat)
    maybeGivee = drawPuck(ageHat)
    aDiscards = Set()
  }

  def selectNewGiver(): Unit = {
    val giver: String = maybeGiver.get
    agrHat = Hats.removePuck(giver)(agrHat)
    ageHat = Hats.returnDiscards(aDiscards)(ageHat)
    aDiscards = Set()
    maybeGiver = drawPuck(agrHat)
    maybeGivee = drawPuck(ageHat)
  }

  def giveeIsSuccess(): Unit = {
    val giver: String = maybeGiver.get
    val givee: String = maybeGivee.get
    aPlayers = Players.updateGivee(giver)(agYear)(givee)(aPlayers)
    aPlayers = Players.updateGiver(givee)(agYear)(giver)(aPlayers)
    ageHat = Hats.removePuck(givee)(ageHat)
    maybeGivee = None
  }

  def giveeIsFailure(): Unit = {
    val givee: String = maybeGivee.get
    ageHat = Hats.removePuck(givee)(ageHat)
    aDiscards = Hats.discardGivee(givee)(aDiscards)
    maybeGivee = drawPuck(ageHat)
  }

  def errors(): Seq[String] = {
    val plrKeys: Seq[String] = aPlayers.keys.toSeq
    val plrErrors = {
      for {
        plrSym <- plrKeys
        giverCode = Players.getGiver(plrSym)(agYear)(aPlayers)
        giveeCode = Players.getGivee(plrSym)(agYear)(aPlayers)
        if plrSym == giverCode || plrSym == giveeCode
      } yield plrSym
    }
    plrErrors.sorted
  }

  def printResults(): Unit = {
    val plrKeys: Seq[String] = aPlayers.keys.toSeq.sorted
    for (plrSym <- plrKeys) yield {
      val playerName = Players.getPlayerName(plrSym)(aPlayers)
      val giveeCode = Players.getGivee(plrSym)(agYear)(aPlayers)
      val giveeName = Players.getPlayerName(giveeCode)(aPlayers)
      val giverCode = Players.getGiver(plrSym)(agYear)(aPlayers)

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
  }

  def printStringGivingRoster(rName: String)(rYear: Int): Unit = {
    println()
    println("%s - Year %d Gifts:".format(rName, rYear + agYear))
    println()
    if (errors().nonEmpty) {
      println()
      println("There is a logic error in this year's pairings.")
      println("Do you see it?")
      println("If not... call me and I'll explain!")
      println()
      println()
    }
    printResults()
  }

  def printAndAsk(rName: String)(rYear: Int): String = {
    printStringGivingRoster(rName)(rYear)
    println()
    readLine("Continue? ('q' to quit): ")
  }
}
