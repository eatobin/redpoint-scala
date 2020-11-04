package redpoint

import spray.json._

case class Roster(rosterName: String, rosterYear: Int, players: Map[Symbol, Player])

object Roster extends DefaultJsonProtocol {
  def rosterJsonStringToRoster(s: JsonString): Either[ErrorString, Roster] = {
    try {
      Right(s.parseJson.convertTo[Roster])
    } catch {
      case _: Exception =>
        Left("JSON parse error.")
    }
  }

  implicit val rosterFormat: RootJsonFormat[Roster] = jsonFormat3(Roster.apply)
}
