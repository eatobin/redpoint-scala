package redpoint

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def setGiftHistory(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)

  def addYearPlayer(player: Player, playerKey: PlayerKey): Player = {
    val gh = player.giftHistory
    val ngh = GiftHistory.addYear(gh, playerKey)
    setGiftHistory(player, ngh)
  }
}
