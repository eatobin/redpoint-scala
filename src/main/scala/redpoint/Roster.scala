package redpoint

import spray.json._

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster extends DefaultJsonProtocol {
  def rosterJsonStringToRoster(s: Either[ErrorString, String]): Either[ErrorString, Roster] = {
    s match {
      case Right(r) =>
        try {
          Right(r.parseJson.convertTo[Roster])
        } catch {
          case _: Exception =>
            Left("JSON parse error.")
        }
      case Left(l) =>
        Left(l)
    }
  }

  implicit val rosterFormat: RootJsonFormat[Roster] = jsonFormat3(Roster.apply)
}
