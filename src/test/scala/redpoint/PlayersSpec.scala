package redpoint

import io.circe.Error
import org.scalatest.flatspec.AnyFlatSpec

class PlayersSpec extends AnyFlatSpec {

  private val jsonStringPlrs: JsonString = "{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
  private val players: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)

  private val newBee: Player = Player("New Bee", Vector(GiftPair("NewBee", "NewBee")))
  private val newBeePlayers: Map[String, Player] =
    Map("RinSta" -> newBee, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)

  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta"), GiftPair("JohLen", "JohLen")))
  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc"), GiftPair("GeoHar", "GeoHar")))
  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen"), GiftPair("PauMcc", "PauMcc")))
  private val playersExt: Map[String, Player] =
    Map("RinSta" -> rinStaExt, "JohLen" -> johLenExt, "GeoHar" -> geoHarExt, "PauMcc" -> pauMccExt)

  private val geoHarGivee: Player = Player("George Harrison", Vector(GiftPair("you", "PauMcc")))
  private val geoHarGiver: Player = Player("George Harrison", Vector(GiftPair("RinSta", "you")))
  private val playersGivee: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHarGivee, "PauMcc" -> pauMcc)
  private val playersGiver: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHarGiver, "PauMcc" -> pauMcc)

  "Players" should "return an updated player" in {
    assert(Players.updatePlayer("RinSta")(Player("New Bee", Vector(GiftPair("NewBee", "NewBee"))))(players) == newBeePlayers)
  }

  it should "return a player name" in {
    assert(Players.getPlayerName("PauMcc")(players) == "Paul McCartney")
  }

  it should "add a new year" in {
    assert(Players.addYear(players) == playersExt)
  }

  it should "return a givee and a giver" in {
    assert(Players.getGivee("GeoHar")(0)(players) == "RinSta")
    assert(Players.getGiver("GeoHar")(0)(players) == "PauMcc")
  }

  it should "update a givee and a giver" in {
    assert(Players.updateGivee("GeoHar")(0)("you")(players) == playersGivee)
    assert(Players.updateGiver("GeoHar")(0)("you")(players) == playersGiver)
  }

  it should "convert from JSON" in {
    val plrsJson: Either[Error, Map[String, Player]] = Players.jsonStringToPlayers(jsonStringPlrs)
    assert(plrsJson == Right(players))
  }
}
