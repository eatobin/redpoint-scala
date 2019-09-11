// :paste ./src/main/scala/redpoint/Roster.scala
// redpoint.Roster.getRosterName(redpoint.Roster.ss)

package redpoint

import redpoint.RosterStringCheck._

case class GiftPair(givee: Givee, giver: Giver)

case class Player(pName: PName, giftHist: GiftHist)

object Roster {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
  val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"

  val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  val plist = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  val pline = List("RinSta", "Ringo Starr", "JohLen", "GeoHar")
  val pkv: PlayerKV = ('RinSta, Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))))
  val plistShort = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"))
  val pkvl: List[PlayerKV] = List(('RinSta, Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar)))))
  val pmap: Map[PlrSym, Player] = Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))),
    'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))),
    'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))),
    'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))

  // Given a scrubbed return the roster name
  def getRosterName(scrubbed: Scrubbed): RName = {
    lines(scrubbed).head.split(",").head
  }

  // Given a scrubbed return the roster year
  def getRosterYear(scrubbed: Scrubbed): RYear = {
    lines(scrubbed).head.split(",").last.toInt
  }

  // Given a scrubbed return the player-list
//  def makePlayersList(scrubbed: Scrubbed): PlayersList = {
//    scrubbed
//      .split("\n")
//      .toList
//      .tail
//      .map(l => l.split(",")
//        .toList)
//  }

  // test
  def makePlayerKV(kv: PlayerLine): PlayerKV =
    kv match {
      case List(s, pn, ge, gr) =>
        val gp = GiftPair(Symbol(ge), Symbol(gr))
        val plr = Player(pn, Vector(gp))
        (Symbol(s), plr)
    }

  // test
  def makePlayerKVList(playersList: PlayersList): PlayersKVList =
    playersList.map(kvp => makePlayerKV(kvp))

  // Returns a hash map linking a player symbol (key) to a player hash map (value)
  //  given a player symbol, name, initial givee and initial giver - all strings
  def makePlayerKVMap(playersKVList: PlayersKVList): PlayersMap =
    playersKVList.toMap

  // Returns a hash map of multiple players given a players-vector
  val makePlayersMap: PlayersList => PlayersMap =
    makePlayerKVMap _ compose makePlayerKVList

  // Returns a player given a players map and a player symbol
  def getPlayerInRoster(ps: PlrSym, pm: PlayersMap): Player =
    pm(ps)

  // Returns a gift history given a player
  def getGiftHistoryInPlayer(plr: Player): GiftHist =
    plr.giftHist

  // Returns a gift pair given a gift history and a gift year
  def getGiftPairInGiftHistory(gh: GiftHist, gy: GYear): GiftPair =
    gh(gy)

  // Returns a gift pair given a player map, a player symbol and a gift year
  def getGiftPairInRoster(ps: PlrSym)(pm: PlayersMap, gy: GYear): GiftPair = {
    val plr = getPlayerInRoster(ps, pm)
    val gh = getGiftHistoryInPlayer(plr)
    getGiftPairInGiftHistory(gh, gy)
  }

  // Returns a givee given a gift pair
  def getGiveeInGiftPair(gp: GiftPair): Givee =
    gp.givee

  // Returns a giver given a gift pair
  def getGiverInGiftPair(gp: GiftPair): Giver =
    gp.giver

  // Returns a gift history with the provided gift pair at the supplied year
  def setGiftPairInGiftHistory(gy: GYear, gp: GiftPair, gh: GiftHist): GiftHist = {
    gh.updated(gy, gp)
  }

  // Sets a gift history into the provided player
  def setGiftHistoryInPlayer(gh: GiftHist, plr: Player): Player =
    plr.copy(giftHist = gh)

  // Returns a gift history with the provided gift pair at the supplied year
  def setGiftPairInRoster(ps: PlrSym, gy: GYear, gp: GiftPair, pm: PlayersMap): PlayersMap = {
    val plr = getPlayerInRoster(ps, pm)
    val gh = getGiftHistoryInPlayer(plr)
    val ngh = setGiftPairInGiftHistory(gy, gp, gh)
    val nplr = setGiftHistoryInPlayer(ngh, plr)
    pm.updated(ps, nplr)
  }
}
