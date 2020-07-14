package redpoint

import spray.json._

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster extends DefaultJsonProtocol {
  implicit val rosterFormat: RootJsonFormat[Roster] = jsonFormat3(Roster.apply)
}
