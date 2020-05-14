package redpoint

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def playerUpdateGiftHistory(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)

  def playerAddYear(player: Player, playerKey: PlayerKey): Player = {
    val gh = player.giftHistory
    val ngh = GiftHistory.giftHistoryAddYear(gh, playerKey)
    playerUpdateGiftHistory(player, ngh)
  }
}
