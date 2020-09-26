package redpoint

import spray.json._

case class Player(playerName: PlayerName, giftHistory: GiftHistory)

object Player extends DefaultJsonProtocol {
  def playerUpdateGiftHistory(player: Player, giftHistory: GiftHistory): Player =
    player.copy(giftHistory = giftHistory)

  def playerJsonStringToPlayer(plrString: String): Player = plrString.parseJson.convertTo[Player]

  implicit val playerFormat: RootJsonFormat[Player] = jsonFormat2(Player.apply)
}
