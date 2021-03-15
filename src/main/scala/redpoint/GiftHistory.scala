//package redpoint
//
//import io.circe.Error
//import io.circe.generic.auto._
//import io.circe.parser._
//import io.circe.syntax.EncoderOps
//
//object GiftHistory {
//  def giftHistoryAddYear(giftHistory: Vector[GiftPair])(playerKey: String): Vector[GiftPair] =
//    giftHistory :+ GiftPair(playerKey, playerKey)
//
//  def giftHistoryUpdateGiftHistory(giftHistory: Vector[GiftPair], giftYear: Int, giftPair: GiftPair): Vector[GiftPair] =
//    giftHistory.updated(giftYear, giftPair)
//
//  def giftHistoryJsonStringToGiftHistory(ghString: String): Either[Error, Vector[GiftPair]] =
//    decode[Vector[GiftPair]](ghString)
//}
