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

  def readFileIntoString(fp: FilePath): Either[ErrorString, String] =
    try {
      val bufferedSource = Source.fromFile(fp)
      val js = bufferedSource.getLines().mkString
      bufferedSource.close
      Right(js)
    } catch {
      case _: Exception => Left("File read error. File: " ++ fp ++ " does not exist.")
    }

  private def random[T](s: Set[T]): T = {
    val n = util.Random.nextInt(s.size)
    s.iterator.drop(n).next()
  }

  def drawPuck(hat: Hat): Option[PlayerKey] = {
    if (hat.nonEmpty) {
      Some(random(hat))
    } else {
      None
    }
  }
}
