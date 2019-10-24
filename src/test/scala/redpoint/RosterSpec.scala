package redpoint

import org.scalatest.FlatSpec

class RosterSpec extends FlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  private val players: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

  private val roster: Roster = Roster("The Beatles", 2014, players)

  "A roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  //  private val rinStaExt: Player =
  //    Player("Ringo Starr", mutableListOf(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
  //  private val johLenExt: Player =
  //    Player("John Lennon", mutableListOf(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
  //  private val geoHarExt: Player =
  //    Player("George Harrison", mutableListOf(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
  //  private val pauMccExt: Player =
  //    Player("Paul McCartney", mutableListOf(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))
  //
  //  private val playersExt: Players =
  //    mutableMapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)

  //  l pmap: redpoint.PlayersMap = Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))), 'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))), 'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))), 'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))

  //  "A rawString" should "be scrubbed and fully valid" in {
  //    assert(scrubbedRosterString(ss) == valid)
  //    assert(scrubbedRosterString(null) ==
  //      Left("the roster string was null, empty or only spaces"))
  //    assert(scrubbedRosterString("") ==
  //      Left("the roster string was null, empty or only spaces"))
  //    assert(scrubbedRosterString("   ") ==
  //      Left("the roster string was null, empty or only spaces"))
  //    assert(scrubbedRosterString(tooShort) ==
  //      Left("roster string is not long enough"))
  //    assert(scrubbedRosterString(noInfo) ==
  //      Left("the roster info line is blank"))
  //    assert(scrubbedRosterString(noName) ==
  //      Left("the name value is missing"))
  //    assert(scrubbedRosterString(noYear) ==
  //      Left("the year value is missing"))
  //    assert(scrubbedRosterString(yearLetter) ==
  //      Left("the year value is not all digits"))
  //    assert(scrubbedRosterString(yearBig) ==
  //      Left("not 1956 <= year <= 2056"))
  //    assert(scrubbedRosterString(yearSmall) ==
  //      Left("not 1956 <= year <= 2056"))
  //    assert(scrubbedRosterString(badSym) ==
  //      Left("the players sub-string is invalid"))
  //    assert(scrubbedRosterString(missingSym) ==
  //      Left("the players sub-string is invalid"))
  //  }

  //  "A Roster" should "have a name" in {
  //    assert(getRosterName(ss) == "The Beatles")
  //  }
  //
  //  it should "have a year" in {
  //    assert(getRosterYear(ss) == 2014)
  //  }
  //
  //  it should "have players" in {
  //    assert(
  //
  //      makePlayersList(ss) == plist)
  //  }
  //
  //  it should "make a Player from a playerLine" in {
  //    assert(makePlayerKV(pline) == pkv)
  //  }
  //
  //  it should "make a PlayerKVList from a playersList" in {
  //    assert(makePlayerKVList(plistShort) == pkvl)
  //  }
  //
  //  it should "put its players in a Map" in {
  //    assert(makePlayersMap(makePlayersList(ss)) ==
  //      Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))),
  //        'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))),
  //        'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))),
  //        'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))))
  //  }
  //
  //
  //  it should "return Paul McCartney if asked" in {
  //    assert(getPlayerInRoster('PauMcc, pmap) ==
  //      Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))
  //  }
  //
  //  it should "update a giftPair correctly" in {
  //    assert(setGiftPairInRoster('RinSta, 0, GiftPair('RinSta, 'RinSta), pmap) ==
  //      Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('RinSta, 'RinSta))),
  //        'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))),
  //        'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))),
  //        'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))))
  //  }
}
