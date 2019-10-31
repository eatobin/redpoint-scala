package redpoint

object Players {
  def getPlayer(players: Players, playerKey: PlayerKey): Player =
    players(playerKey)

  def setPlayer(players: Players, playerKey: PlayerKey, player: Player): Players = {
    players.updated(playerKey, player)
  }

//  ;(defn add-year-in-roster
//  ;  "Add a year for each player in roster"
//  ;  [plrs-map]
//  ;  (into {}
//  ;        (for [[k v] plrs-map]
//  ;          [k (add-year-in-player v k)])))
}
