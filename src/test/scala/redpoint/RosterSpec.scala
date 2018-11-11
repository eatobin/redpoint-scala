package redpoint

import org.scalatest.FlatSpec

class RosterSpec extends FlatSpec {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
  val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  val badRL = List(List(""))
  val plist = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  val pmap: redpoint.PlayersMap = Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))), 'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))), 'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))), 'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))

  "A RosterList" should "create itself properly" in {
    assert(RosterUtility.makeRosterList(bs) == rl)
  }

  "A RosterInfo" should "create itself properly" in {
    assert(RosterUtility.makeRosterInfo(rl) == List("The Beatles", "2014"))
    assert(RosterUtility.makeRosterInfo(badRL) == List("Is", "Empty"))
  }

  "A PlayersList" should "create itself properly" in {
    assert(RosterUtility.makePlayersList(rl) == plist)
    assert(RosterUtility.makePlayersList(badRL) == List(List("Is"), List("Empty")))
  }

  "A PlayersMap" should "create itself properly" in {
    assert(RosterUtility.makePlayersMap(plist) == pmap)
  }

  "getPlayerInRoster" should "return Paul McCartney" in {
    assert(RosterUtility.getPlayerInRoster('PauMcc)(pmap) == Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))
  }

  "A giftPair" should "update correctly" in {
    assert(RosterUtility.setGiftPairInRoster('RinSta)(0)(GiftPair('RinSta, 'RinSta))(pmap) ==
      Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('RinSta, 'RinSta))),
        'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))),
        'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))),
        'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))))
  }
}
