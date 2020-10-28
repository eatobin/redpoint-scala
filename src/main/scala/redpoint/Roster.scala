package redpoint

import spray.json._

case class Roster(rosterName: String, rosterYear: Int, players: Map[Symbol, Player])

object Roster extends DefaultJsonProtocol {
  def rosterJsonStringToRoster(s: Either[Unit, JsonString]): Either[ErrorString, Roster] = {
    s match {
      case Right(r) =>
        try {
          Right(r.parseJson.convertTo[Roster])
        } catch {
          case _: Exception =>
            Left("JSON parse error.")
        }
      case Left(_) =>
        Left("No such file.")
    }
  }

  implicit val rosterFormat: RootJsonFormat[Roster] = jsonFormat3(Roster.apply)
}
