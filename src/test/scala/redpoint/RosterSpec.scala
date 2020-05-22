package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Roster._

class RosterSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)
  private val roster: Roster = Roster("The Beatles", 2014, players)

  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("RinSta"), Symbol("RinSta"))))
  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta")), GiftPair(Symbol("JohLen"), Symbol("JohLen"))))
  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc")), GiftPair(Symbol("GeoHar"), Symbol("GeoHar"))))
  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen")), GiftPair(Symbol("PauMcc"), Symbol("PauMcc"))))
  private val playersExt: Players =
    Map(Symbol("RinSta") -> rinStaExt, Symbol("JohLen") -> johLenExt, Symbol("GeoHar") -> geoHarExt, Symbol("PauMcc") -> pauMccExt)
  private val rosterExt: Roster = Roster("The Beatles", 2014, playersExt)

  "A Roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "return players" in {
    assert(roster.players == players)
  }

  it should "return a player name" in {
    assert(Roster.rosterGetPlayerName(roster, Symbol("PauMcc")) == "Paul McCartney")
  }

  it should "add a new year" in {
    assert(rosterAddYear(roster) == rosterExt)
  }

  it should "return a givee and a giver" in {
    assert(rosterGetGivee(roster, Symbol("GeoHar"), 0) == Symbol("RinSta"))
    assert(rosterGetGiver(roster, Symbol("GeoHar"), 0) == Symbol("PauMcc"))
  }
}
