package redpoint

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def setGiftHistory(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)
}
