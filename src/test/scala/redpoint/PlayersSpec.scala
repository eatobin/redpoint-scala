package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Players._

class PlayersSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("RinSta"), Symbol("RinSta"))))
  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta")), GiftPair(Symbol("JohLen"), Symbol("JohLen"))))
  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc")), GiftPair(Symbol("GeoHar"), Symbol("GeoHar"))))
  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen")), GiftPair(Symbol("PauMcc"), Symbol("PauMcc"))))
  private val playersExt: Players =
    Map(Symbol("RinSta") -> rinStaExt, Symbol("JohLen") -> johLenExt, Symbol("GeoHar") -> geoHarExt, Symbol("PauMcc") -> pauMccExt)

  private val newBee: Player = Player("New Bee", Vector(GiftPair(Symbol("NewBee"), Symbol("NewBee"))))
  private val newBeePlayers: Players =
    Map(Symbol("RinSta") -> newBee, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  "Players" should "return an updated player" in {
    assert(updatePlayer(players, Symbol("RinSta"), Player("New Bee", Vector(GiftPair(Symbol("NewBee"), Symbol("NewBee"))))) == newBeePlayers)
  }

  it should "return a player name" in {
    assert(getPlayerName(players, Symbol("PauMcc")) == "Paul McCartney")
  }

  it should "add a new year" in {
    assert(addYear(players) == playersExt)
  }

  it should "return a givee and a giver" in {
    assert(getGivee(players, Symbol("GeoHar"), 0) == Symbol("RinSta"))
    assert(getGiver(players, Symbol("GeoHar"), 0) == Symbol("PauMcc"))
  }
}
