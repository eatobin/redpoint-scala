package redpoint

import spray.json._
import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster extends DefaultJsonProtocol {
  implicit val rosterFormat: RootJsonFormat[Roster] = jsonFormat3(Roster.apply)
}
