package redpoint

case class GiftPair(givee: Givee, giver: Giver)

case class Player(pName: String, giftHist: Vector[GiftPair])

object RosterUtility {

  def makeRosterList(rosterString: String): RosterList = {
    val rosterLines = rosterString.split("\n").toList
    rosterLines.map(l => l.split(", ").toList)
  }

  def makeRosterInfo(rosterList: RosterList): List[String] =
    rosterList.head

  def makePlayersList(rosterList: RosterList): RosterList =
    rosterList.tail

  def makePlayerKV(kv: List[String]): (Symbol, Player) =
    kv match {
      case List(s, pn, ge, gr) =>
        val gp = GiftPair(Symbol(ge), Symbol(gr))
        val plr = Player(pn, Vector(gp))
        (Symbol(s), plr)
    }

  def makePlayersMapList(rosterList: RosterList): Map[Symbol, Player] =
    rosterList.map(kvt => makePlayerKV(kvt)).toMap

  val makePlayersMap: (RosterList) => Map[Symbol, Player] =
    makePlayersMapList _ compose makePlayersList

  def third(d: Double): Boolean = d < 10.0

  def second(s: String): Double = s.toDouble

  def first(i: Int): String = i.toString

  val secondComposeFirst: (Int) => Double = second _ compose first

  val thirdComposeFirst: (Int) => Boolean = third _ compose second compose first

}


// :load /Users/eatobin/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala
// :load /home/eric/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala

//val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
//val rl: List[List[String]] = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
//val rl: List[List[String]] = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))//val pl: List[List[String]] = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
