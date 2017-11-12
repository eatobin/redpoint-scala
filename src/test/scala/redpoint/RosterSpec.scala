package redpoint

import org.scalatest.FlatSpec

class RosterSpec extends FlatSpec {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val rl = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))
  val badRL = List(List(""))
  val plist = List(List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))

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
}
