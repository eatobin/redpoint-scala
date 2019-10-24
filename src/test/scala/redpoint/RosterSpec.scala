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
  //  mutableMapOf("RinSta" to rinStaExt, "JohLen" to johLenExt, "GeoHar" to geoHarExt, "PauMcc" to pauMccExt)

  "A roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "return players" in {
    assert(roster.players == players)
  }

}
