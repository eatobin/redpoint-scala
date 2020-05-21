// :paste ./src/main/scala/redpoint/Roster.scala
// redpoint.Roster.getRosterName(redpoint.Roster.ss)

package redpoint

case class Roster(rosterName: RosterName, rosterYear: RosterYear, players: Players)

object Roster {
  def getPlayerName(roster: Roster, playerKey: PlayerKey): PlayerName = {
    roster.players(playerKey).playerName
  }
}
