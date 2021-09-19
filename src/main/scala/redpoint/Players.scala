package redpoint

import io.circe.Error
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

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

  def getGivee(selfKey: String)(giftYear: Int)(players: Map[String, Player]): String =
    players(selfKey).giftHistory(giftYear).givee

  def getGiver(selfKey: String)(giftYear: Int)(players: Map[String, Player]): String =
    players(selfKey).giftHistory(giftYear).giver

  private def setGiftPair(playerKey: String)(giftYear: Int)(giftPair: GiftPair)(players: Map[String, Player]): Map[String, Player] = {
    val ngh = GiftHistory.updateGiftHistory(giftYear)(giftPair)(players(playerKey).giftHistory)
    val nplr = Player.updateGiftHistory(ngh)(players(playerKey))
    updatePlayer(playerKey)(nplr)(players)
  }

  def updateGivee(selfKey: String)(giftYear: Int)(givee: String)(players: Map[String, Player]): Map[String, Player] = {
    val ngp = GiftPair.updateGivee(givee)(players(selfKey).giftHistory(giftYear))
    setGiftPair(selfKey)(giftYear)(ngp)(players)
  }

  def updateGiver(selfKey: String)(giftYear: Int)(giver: String)(players: Map[String, Player]): Map[String, Player] = {
    val ngp = GiftPair.updateGiver(giver)(players(selfKey).giftHistory(giftYear))
    setGiftPair(selfKey)(giftYear)(ngp)(players)
  }

  def jsonStringToPlayers(plrsString: String): Either[Error, Map[String, Player]] =
    decode[Map[String, Player]](plrsString)
}
