case class GiftPair(givee: Symbol, giver: Symbol)

case class Player(pName: String, giftHist: Vector[GiftPair])

object RosterUtility {

  def makeRosterList(rosterString: String): List[List[String]] = {
    val rosterLines = rosterString.split("\n").toList
    rosterLines.map(l => l.split(", ").toList)
  }

  def makeRosterInfo(rosterList: List[List[String]]): List[String] =
    rosterList.head

  def makePlayersList(rosterList: List[List[String]]): List[List[String]] =
    rosterList.tail

  def makePlayerKV(kv: List[String]): (Symbol, Player) =
    kv match {
      case List(s, pn, ge, gr) =>
        val gp = GiftPair(Symbol(ge), Symbol(gr))
        val plr = Player(pn, Vector(gp))
        (Symbol(s), plr)
    }

  def makePlayersMapList(rosterList: List[List[String]]): Map[Symbol, Player] =
    rosterList.map(kvt => makePlayerKV(kvt)).toMap

}


// :load /Users/eatobin/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala
// :load /home/eric/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala
// val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
// val rl = RosterUtility.makeRosterList(bs)
// val pl = RosterUtility.makePlayersList(rl)
// val hd = pl.head
// val kv = RosterUtility.makePlayerKV(hd)
