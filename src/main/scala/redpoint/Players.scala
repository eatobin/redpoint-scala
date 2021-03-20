package redpoint

import io.circe.Error
import io.circe.generic.decoding.DerivedDecoder.deriveDecoder
import io.circe.parser._

object Players {
  def updatePlayer(playerKey: String)(player: Player)(players: Map[String, Player]): Map[String, Player] =
    players.updated(playerKey, player)

  def getPlayerName(playerKey: String)(players: Map[String, Player]): String =
    players(playerKey).playerName

  def addYear(players: Map[String, Player]): Map[String, Player] = {
    val nplrs = for ((playerKey, player) <- players) yield {
      val gh = player.giftHistory
      val ngh = GiftHistory.addYear(playerKey)(gh)
      val nplr = Player.updateGiftHistory(ngh)(player)
      playerKey -> nplr
    }
    nplrs
  }

  def getGivee(giver: String)(giftYear: Int)(players: Map[String, Player]): String =
    players(giver).giftHistory(giftYear).givee

  def getGiver(givee: String)(giftYear: Int)(players: Map[String, Player]): String =
    players(givee).giftHistory(giftYear).giver

  private def setGiftPair(playerKey: String)(giftYear: Int)(giftPair: GiftPair)(players: Map[String, Player]): Map[String, Player] = {
    val ngh = GiftHistory.updateGiftHistory(giftYear)(giftPair)(players(playerKey).giftHistory)
    val nplr = Player.updateGiftHistory(ngh)(players(playerKey))
    updatePlayer(playerKey)(nplr)(players)
  }

  def updateGivee(giver: String)(giftYear: Int)(givee: String)(players: Map[String, Player]): Map[String, Player] = {
    val ngp = GiftPair.updateGivee(givee)(players(giver).giftHistory(giftYear))
    setGiftPair(giver)(giftYear)(ngp)(players)
  }

  def updateGiver(givee: String)(giftYear: Int)(giver: String)(players: Map[String, Player]): Map[String, Player] = {
    val ngp = GiftPair.updateGiver(giver)(players(givee).giftHistory(giftYear))
    setGiftPair(givee)(giftYear)(ngp)(players)
  }

  def jsonStringToPlayers(plrsString: String): Either[Error, Map[String, Player]] =
    decode[Map[String, Player]](plrsString)
}
