package redpoint

import scala.io.Source

object Main {
  var agYear = 0
  var aGiver: Symbol = _
  var aGivee: Symbol = _
  var aPlayers = Map()
  var agrHat = Set()
  var ageHat = Set()
  var aDiscards = Set()
  var aRosterName = ""
  var aRosterYear = 0
  var filePath = "resources/blackhawks.json"

  def readFileIntoString(fp: String): Either[Unit, JsonString] =
    try {
      val bufferedSource = Source.fromFile(fp)
      val js = bufferedSource.getLines().mkString
      bufferedSource.close
      Right(js)
    } catch {
      case _: Exception =>
        println("File read error. File: " ++ fp ++ " does not exist.")
        Left(sys.exit(0))
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
}
