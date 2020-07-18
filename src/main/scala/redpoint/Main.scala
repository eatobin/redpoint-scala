package redpoint

import scala.io.Source
import spray.json._

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

  def readFileIntoJsRoster(fp: FilePath): Either[ErrorString, JsRoster] =
    try {
      val bufferedSource = Source.fromFile(fp)
      val js = bufferedSource.getLines.mkString
      bufferedSource.close
      Right(js.parseJson)
    } catch {
      case _: Exception => Left("File read error. File: " ++ fp ++ " does not exist.")
    }
}
