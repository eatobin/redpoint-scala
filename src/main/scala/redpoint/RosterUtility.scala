package redpoint

case class GiftPair(givee: Givee, giver: Giver)

case class Player(pName: PName, giftHist: GiftHist)

object RosterUtility {

  def makeRosterList(rosterString: RosterString): RosterList = {
    val rosterLines = rosterString.split("\n").toList
    rosterLines.map(l => l.split(", ").toList)
  }

  def makeRosterInfo(rosterList: RosterList): InfoLine = {
    rosterList.head match {
      case List("") => List("Is", "Empty")
      case rl => rl

    }
  }

  def makePlayersList(rosterList: RosterList): PlayersList = {
    rosterList.tail match {
      case List() => List(List("Is"), List("Empty"))
      case rl => rl
    }
  }

  def makePlayerKV(kv: PlayerLine): PlayerKV =
    kv match {
      case List(s, pn, ge, gr) =>
        val gp = GiftPair(Symbol(ge), Symbol(gr))
        val plr = Player(pn, Vector(gp))
        (Symbol(s), plr)
    }

  def makePlayerKVList(playersList: PlayersList): PlayersKVList =
    playersList.map(kvp => makePlayerKV(kvp))

  def makePlayerKVMap(playersKVList: PlayersKVList): PlayersMap =
    playersKVList.toMap

  val makePlayersMap: (PlayersList) => PlayersMap =
    makePlayerKVMap _ compose makePlayerKVList

  def getPlayerInRoster(ps: PlrSym)(pm: PlayersMap): Player =
    pm(ps)

  def getGiftHistoryInPlayer(plr: Player): GiftHist =
    plr.giftHist

  def getGiftPairInGiftHistory(gh: GiftHist)(gy: GYear): GiftPair =
    gh(gy)

  def getGiftPairInRoster(ps: PlrSym)(pm: PlayersMap)(gy: GYear): GiftPair = {
    val plr = getPlayerInRoster(ps)(pm)
    val gh = getGiftHistoryInPlayer(plr)
    getGiftPairInGiftHistory(gh)(gy)
  }

}

// :paste /home/eric/scala_projects/redpoint-scala/src/main/scala/redpoint/RosterUtility.scala
// :paste /Users/eatobin/scala_projects/redpoint-scala/src/main/scala/redpoint/RosterUtility.scala

// val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
// val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
// val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
// val plist = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
// val pline = List("RinSta", "Ringo Starr", "JohLen", "GeoHar")
// val pkv: redpoint.PlayerKV = ('RinSta,Player("Ringo Starr",Vector(GiftPair('JohLen,'GeoHar))))
// val pkvl: List[redpoint.PlayerKV] = List(('RinSta,Player(Ringo Starr,Vector(GiftPair('JohLen,'GeoHar)))))
// val pmap: redpoint.PlayersMap = Map('RinSta -> Player(Ringo Starr,Vector(GiftPair('JohLen,'GeoHar))), 'JohLen -> Player(John Lennon,Vector(GiftPair('PauMcc,'RinSta))), 'GeoHar -> Player(George Harrison,Vector(GiftPair('RinSta,'PauMcc))), 'PauMcc -> Player(Paul McCartney,Vector(GiftPair('GeoHar,'JohLen))))

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
