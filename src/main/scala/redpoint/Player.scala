package redpoint

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

case class Player(playerName: String, giftHistory: Vector[GiftPair])

object Player {
  def updateGiftHistory(giftHistory: Vector[GiftPair])(player: Player): Player =
    player.copy(giftHistory = giftHistory)

  def jsonStringToPlayer(plrString: String): Either[Error, Player] =
    decode[Player](plrString)

  def playerToJsonString(player: Player): JsonString =
    player.asJson.noSpaces
}
