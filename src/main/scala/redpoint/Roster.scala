package redpoint

import io.circe.generic.auto._
import io.circe.parser._

case class Roster(rosterName: String, rosterYear: Int, players: Map[Symbol, Player])

object Roster {
  def rosterJsonStringToRoster(s: Either[ErrorString, JsonString]): Either[ErrorString, Roster] = {
    s match {
      case Right(rFile) =>
        val res = decode[Roster](rFile)
        res match {
          case Right(rDecode) => Right(rDecode)
          case Left(lDecode) => Left(lDecode.toString)
        }
      case Left(lFile) =>
        Left(lFile)
    }
  }
}
