package redpoint

import spray.json._

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player extends DefaultJsonProtocol {
  def playerUpdateGiftHistory(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)

  implicit val playerFormat: RootJsonFormat[Player] = jsonFormat2(Player.apply)
}
