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

  private val geoHarGivee: Player = Player("George Harrison", Vector(GiftPair(Symbol("you"), Symbol("PauMcc"))))
  private val geoHarGiver: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("you"))))
  private val playersGivee: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHarGivee, Symbol("PauMcc") -> pauMcc)
  private val playersGiver: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHarGiver, Symbol("PauMcc") -> pauMcc)

  "A Roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "return a player name" in {
    assert(Roster.getPlayerName(players, Symbol("PauMcc")) == "Paul McCartney")
  }

  it should "add a new year" in {
    assert(addYear(players) == playersExt)
  }

  it should "return a givee and a giver" in {
    assert(getGivee(players, Symbol("GeoHar"), 0) == Symbol("RinSta"))
    assert(getGiver(players, Symbol("GeoHar"), 0) == Symbol("PauMcc"))
  }

  it should "update a givee and a giver" in {
    assert(updateGivee(players, Symbol("GeoHar"), 0, Symbol("you")) == playersGivee)
    assert(updateGiver(players, Symbol("GeoHar"), 0, Symbol("you")) == playersGiver)
  }
}
