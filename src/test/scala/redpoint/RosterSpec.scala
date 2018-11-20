package redpoint

import org.scalatest.FlatSpec
import redpoint.Roster._
import redpoint.RosterStringCheck._

class RosterSpec extends FlatSpec {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
  val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"
  val valid = Right("The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen")
  val tooShort = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc"
  val noInfo = "\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val noName = ",2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val noYear = "The Beatles\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val yearLetter = "The Beatles, 2014P\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val yearBig = "The Beatles, 2096\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val yearSmall = "The Beatles, 1896\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val badSym = "The Beatles, 2014\nRinStaX, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val missingSym = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"


  //  val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  //  val badRL = List(List(""))
  //  val plist = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  //  val pmap: redpoint.PlayersMap = Map('RinSta -> Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar))), 'JohLen -> Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta))), 'GeoHar -> Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))), 'PauMcc -> Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen))))

  "A rawString" should "be scrubbed and fully valid" in {
    assert(scrubbedRosterString(ss) == valid)
    assert(scrubbedRosterString(null) ==
      Left("the roster string was null, empty or only spaces"))
    assert(scrubbedRosterString("") ==
      Left("the roster string was null, empty or only spaces"))
    assert(scrubbedRosterString("   ") ==
      Left("the roster string was null, empty or only spaces"))
    assert(scrubbedRosterString(tooShort) ==
      Left("roster string is not long enough"))
    assert(scrubbedRosterString(noInfo) ==
      Left("the roster info line is blank"))
    assert(scrubbedRosterString(noName) ==
      Left("the name value is missing"))
    assert(scrubbedRosterString(noYear) ==
      Left("the year value is missing"))
    assert(scrubbedRosterString(yearLetter) ==
      Left("the year value is not all digits"))
    assert(scrubbedRosterString(yearBig) ==
      Left("not 1956 <= year <= 2056"))
    assert(scrubbedRosterString(yearSmall) ==
      Left("not 1956 <= year <= 2056"))
    assert(scrubbedRosterString(badSym) ==
      Left("the players sub-string is invalid"))
    assert(scrubbedRosterString(missingSym) ==
      Left("the players sub-string is invalid"))
  }

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
