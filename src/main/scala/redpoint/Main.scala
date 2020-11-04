package redpoint

import scala.io.Source

object Main {
  var agYear: Int = 0
  var aGiver: Option[Symbol] = None
  var aGivee: Option[Symbol] = None
  var aPlayers: Map[Symbol, Player] = Map()
  var agrHat: Set[Symbol] = Set()
  var ageHat: Set[Symbol] = Set()
  var aDiscards: Set[Symbol] = Set()
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
      case _: Exception =>
        Left("File read error. File: " ++ fp ++ " does not exist.")
    }

  def rosterOrQuit(fp: String): Unit = {
    val rosterStringEither = readFileIntoJsonString(fp)
    rosterStringEither match {
      case Right(rs) =>
        val rosterEither = Roster.rosterJsonStringToRoster(rs)
        rosterEither match {
          case Right(r) =>
            aRosterName = r.rosterName
            aRosterYear = r.rosterYear
            aPlayers = r.players
          case Left(pe) =>
            println(pe)
            sys.exit(0)
        }
      case Left(fe) =>
        println(fe)
        sys.exit(0)
    }
  }

  private def random[T](s: Set[T]): T = {
    val n = util.Random.nextInt(s.size)
    s.iterator.drop(n).next()
  }

  def drawPuck(hat: Set[Symbol]): Option[Symbol] = {
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
