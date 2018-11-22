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
  def makePlayersList(scrubbed: Scrubbed): PlayersList = {
    scrubbed
      .split("\n")
      .toList
      .tail
      .map(l => l.split(",")
        .toList)
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

  val makePlayersMap: PlayersList => PlayersMap =
    makePlayerKVMap _ compose makePlayerKVList

  def getPlayerInRoster(ps: PlrSym, pm: PlayersMap): Player =
    pm(ps)

  def getGiftHistoryInPlayer(plr: Player): GiftHist =
    plr.giftHist

  def getGiftPairInGiftHistory(gh: GiftHist, gy: GYear): GiftPair =
    gh(gy)

  def getGiftPairInRoster(ps: PlrSym)(pm: PlayersMap, gy: GYear): GiftPair = {
    val plr = getPlayerInRoster(ps, pm)
    val gh = getGiftHistoryInPlayer(plr)
    getGiftPairInGiftHistory(gh, gy)
  }

  def getGiveeInGiftPair(gp: GiftPair): Givee =
    gp.givee

  def getGiverInGiftPair(gp: GiftPair): Giver =
    gp.giver

  def setGiftPairInGiftHistory(gy: GYear, gp: GiftPair, gh: GiftHist): GiftHist = {
    gh.updated(gy, gp)
  }

  def setGiftHistoryInPlayer(gh: GiftHist, plr: Player): Player =
    plr.copy(giftHist = gh)

  def setGiftPairInRoster(ps: PlrSym, gy: GYear, gp: GiftPair, pm: PlayersMap): PlayersMap = {
    val plr = getPlayerInRoster(ps, pm)
    val gh = getGiftHistoryInPlayer(plr)
    val ngh = setGiftPairInGiftHistory(gy, gp, gh)
    val nplr = setGiftHistoryInPlayer(ngh, plr)
    pm.updated(ps, nplr)
  }
}
