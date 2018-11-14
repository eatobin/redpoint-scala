package redpoint

import org.scalatest.FlatSpec
import redpoint.Roster._

class RosterSpec extends FlatSpec {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
  val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"
  //  val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  //  val badRL = List(List(""))
  //  val plist = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  //  val pmap: redpoint.PlayersMap = Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))), 'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))), 'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))), 'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))

  "A Roster" should "have a name" in {
    assert(getRosterName(ss) == "The Beatles")
  }

  it should "have a year" in {
    assert(getRosterYear(ss) == 2014)
  }
  //
  //  "A RosterInfo" should "create itself properly" in {
  //    assert(Roster.makeRosterInfo(rl) == List("The Beatles", "2014"))
  //    assert(Roster.makeRosterInfo(badRL) == List("Is", "Empty"))
  //  }
  //
  //  "A PlayersList" should "create itself properly" in {
  //    assert(Roster.makePlayersList(rl) == plist)
  //    assert(Roster.makePlayersList(badRL) == List(List("Is"), List("Empty")))
  //  }
  //
  //  "A PlayersMap" should "create itself properly" in {
  //    assert(Roster.makePlayersMap(plist) == pmap)
  //  }
  //
  //  "getPlayerInRoster" should "return Paul McCartney" in {
  //    assert(Roster.getPlayerInRoster('PauMcc)(pmap) == Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))
  //  }
  //
  //  "A giftPair" should "update correctly" in {
  //    assert(Roster.setGiftPairInRoster('RinSta)(0)(GiftPair('RinSta, 'RinSta))(pmap) ==
  //      Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('RinSta, 'RinSta))),
  //        'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))),
  //        'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))),
  //        'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))))
  //  }
}
