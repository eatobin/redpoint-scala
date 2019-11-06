package redpoint

case class Player(playerName: PlayerNameT, giftHistory: GiftHistoryT)

object Player {
  def addYearPlayer(player: Player, playerKey: PlayerKeyT): Player = {
    val gh = player.giftHistory
    val ngh = GiftHistory.addYear(gh, playerKey)
    setGiftHistory(player, ngh)
  }

  def setGiftHistory(player: Player, giftHistory: GiftHistoryT): Player =
    player.copy(giftHistory = giftHistory)
}
