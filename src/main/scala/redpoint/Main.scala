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

  def rosterOrQuit(fp: FilePath): Either[ErrorString, JsonString] =
    try {
      val bufferedSource = Source.fromFile(fp)
      val js = bufferedSource.getLines.mkString
      bufferedSource.close
      Right(js)
    } catch {
      case _: Exception => Left("File read error. File: " ++ fp ++ " does not exist.")
    }

  //  def jsonStringToBorrowers(s: Either[ErrorString, JsonString]): Either[ErrorString, Roster] = {
  //    s match {
  //      case Right(r) =>
  //        try {
  //          Right(r.parseJson.convertTo[Roster])
  //        } catch {
  //          case _: Exception =>
  //            Left("JSON parse error.")
  //        }
  //      case Left(l) =>
  //        Left(l)
  //    }
  //  }
}
