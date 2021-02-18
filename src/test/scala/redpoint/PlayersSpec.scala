package redpoint

import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Players._

class PlayersSpec extends AnyFlatSpec {

  private val jsonStringPlrs: String = "{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  private val newBee: Player = Player("New Bee", Vector(GiftPair(Symbol("NewBee"), Symbol("NewBee"))))
  private val newBeePlayers: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> newBee, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("RinSta"), Symbol("RinSta"))))
  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta")), GiftPair(Symbol("JohLen"), Symbol("JohLen"))))
  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc")), GiftPair(Symbol("GeoHar"), Symbol("GeoHar"))))
  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen")), GiftPair(Symbol("PauMcc"), Symbol("PauMcc"))))
  private val playersExt: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinStaExt, Symbol("JohLen") -> johLenExt, Symbol("GeoHar") -> geoHarExt, Symbol("PauMcc") -> pauMccExt)

  private val geoHarGivee: Player = Player("George Harrison", Vector(GiftPair(Symbol("you"), Symbol("PauMcc"))))
  private val geoHarGiver: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("you"))))
  private val playersGivee: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHarGivee, Symbol("PauMcc") -> pauMcc)
  private val playersGiver: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHarGiver, Symbol("PauMcc") -> pauMcc)

  "Players" should "return an updated player" in {
    assert(playersUpdatePlayer(players, Symbol("RinSta"), Player("New Bee", Vector(GiftPair(Symbol("NewBee"), Symbol("NewBee"))))) == newBeePlayers)
  }

  it should "return a player name" in {
    assert(playersGetPlayerName(players, Symbol("PauMcc")) == "Paul McCartney")
  }

  it should "add a new year" in {
    assert(playersAddYear(players) == playersExt)
  }

  it should "return a givee and a giver" in {
    assert(playersGetGivee(players, Symbol("GeoHar"), 0) == Symbol("RinSta"))
    assert(playersGetGiver(players, Symbol("GeoHar"), 0) == Symbol("PauMcc"))
  }

  it should "update a givee and a giver" in {
    assert(playersUpdateGivee(players, Symbol("GeoHar"), 0, Symbol("you")) == playersGivee)
    assert(playersUpdateGiver(players, Symbol("GeoHar"), 0, Symbol("you")) == playersGiver)
  }

  it should "convert from JSON" in {
    val plrsJson: Map[Symbol, Player] = playersJsonStringToPlayers(jsonStringPlrs)
    assert(plrsJson == players)
  }
}
