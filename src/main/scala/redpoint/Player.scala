package redpoint

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player {
  def playerUpdateGiftHistory(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)
}
