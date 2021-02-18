package redpoint

import scala.io.Source

object Main {
  var agYear: Int = 0
  var aGiver: Option[String] = None
  var aGivee: Option[String] = None
  var aPlayers: Map[String, Player] = Map()
  var agrHat: Set[String] = Set()
  var ageHat: Set[String] = Set()
  var aDiscards: Set[String] = Set()
  var aRosterName: String = ""
  var aRosterYear: Int = 0
  var filePath: String = "resources/blackhawks.json"

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
        val rosterEither = Roster.rosterJsonStringToRoster(Right(rs))
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

  private def random[T](s: Set[T]): T = {
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
    aPlayers = Players.playersAddYear(aPlayers)
    agrHat = Hats.makeHat(aPlayers)
    ageHat = Hats.makeHat(aPlayers)
    aGiver = drawPuck(agrHat)
    aGivee = drawPuck(ageHat)
    aDiscards = Set()
  }
}
