package redpoint

import spray.json._

case class Roster(rosterName: String, rosterYear: Int, players: Map[Symbol, Player])

object Roster extends DefaultJsonProtocol {
  def jsonStringToRoster(s: Either[ErrorString, JsonString]): Either[ErrorString, Roster] = {
    s match {
      case Right(r) =>
        try {
          Right(r.parseJson.convertTo[Roster])
        } catch {
          case e: Exception =>
            Left(e.getMessage)
        }
      case Left(l) =>
        Left(l)
    }
  }

  implicit val rosterFormat: RootJsonFormat[Roster] = jsonFormat3(Roster.apply)
}
