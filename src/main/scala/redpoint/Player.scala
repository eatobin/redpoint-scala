//package redpoint
//
//import io.circe.Error
//import io.circe.generic.auto._
//import io.circe.parser._
//
//case class Player(playerName: String, giftHistory: Vector[GiftPair])
//
//object Player {
//  def playerUpdateGiftHistory(player: Player, giftHistory: Vector[GiftPair]): Player =
//    player.copy(giftHistory = giftHistory)
//
//  def playerJsonStringToPlayer(plrString: String): Either[Error, Player] =
//    decode[Player](plrString)
//}
