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
}
