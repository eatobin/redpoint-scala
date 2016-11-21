case class GiftPair(givee: Symbol, giver: Symbol)

case class Player(pName: String, giftHist: List[GiftPair])

object RosterUtility {

  def makeRosterList(rosterString: String): List[List[String]] = {
    val rosterLines = rosterString.split("\n").toList
    rosterLines.map(l => l.split(", ").toList)
  }

  def makeRosterInfo(rosterList: List[List[String]]): List[String] =
    rosterList.head

  def makePlayersList(rosterList: List[List[String]]): List[List[String]] =
    rosterList.tail

  def f(ab: (Int, Int)): Int =
    ab match {
      case (a, b) => a + b
    }

  def makePlayerMap(kv: (String, String, String, String)): Map[Symbol, Player] =
    kv match {
      case (s, pn, ge, gr) =>
        val gp = GiftPair(Symbol(ge), Symbol(gr))
        val plr = Player(pn, List(gp))
        Map(Symbol(s) -> plr)
    }

}


// :load /Users/eatobin/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala
// :load /home/eric/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala
// testRosterList = [["The Beatles","2014"],["RinSta","Ringo Starr","JohLen","GeoHar"],["JohLen","John Lennon","PauMcc","RinSta"],["GeoHar","George Harrison","RinSta","PauMcc"],["PauMcc","Paul McCartney","GeoHar","JohLen"]]
