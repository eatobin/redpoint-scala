package redpoint

case class GiftPair(givee: Givee, giver: Giver)

case class Player(pName: PName, giftHist: GiftHist)

object RosterUtility {

  def makeRosterList(rosterString: RosterString): RosterList = {
    val rosterLines = rosterString.split("\n").toList
    rosterLines.map(l => l.split(", ").toList)
  }

  def makeRosterInfo(rosterList: RosterList): RosterLine = {
    rosterList.head match {
      case List("") => List("Is", "Empty")
      case rl => rl

    }
  }

  def makePlayersList(rosterList: RosterList): RosterList = {
    rosterList.tail match {
      case List() => List(List("Is"), List("Empty"))
      case rl => rl
    }
  }

  def makePlayerKV(kv: RosterLine): PlayerKV =
    kv match {
      case List(s, pn, ge, gr) =>
        val gp = GiftPair(Symbol(ge), Symbol(gr))
        val plr = Player(pn, Vector(gp))
        (Symbol(s), plr)
    }

  def makePlayersMapList(rosterList: RosterList): PlayersMap =
    rosterList.map(kvp => makePlayerKV(kvp)).toMap

  val makePlayersMap: (RosterList) => PlayersMap =
    makePlayersMapList _ compose makePlayersList

  def getPlayerInRoster(ps: PlrSym, pm: PlayersMap): Player =
    pm(ps)

}

// :paste /home/eric/scala_projects/redpoint-scala/src/main/scala/redpoint/RosterUtility.scala

// val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
// val rl: List[List[String]] = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
// val rl: List[List[String]] = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))//val pl: List[List[String]] = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))

//scala> def f(s: String) = "f(" + s + ")"
//f: (s: String)String
//
//scala> f("one")
//res0: String = f(one)
//
//scala> def g(s: String) = "g(" + s + ")"
//g: (s: String)String
//
//scala> g("two")
//res1: String = g(two)
//
//scala> val fComposeG = f _ compose g _
//fComposeG: String => String = scala.Function1$$Lambda$1005/1149652670@5383bf08
//
//scala> fComposeG("yay")
//res2: String = f(g(yay))
//
//scala>
