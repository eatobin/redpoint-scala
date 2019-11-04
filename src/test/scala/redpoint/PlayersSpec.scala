package redpoint

import org.scalatest.FlatSpec
import redpoint.Players._

class PlayersSpec extends FlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar)))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta)))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc)))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))

  private val newBee: Player = Player("New Bee", Vector(GiftPair('NewBee, 'NewBee)))

  private val players: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

  private val newBeePlayers: Players =
    Map('RinSta -> newBee, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar), GiftPair('RinSta, 'RinSta)))
  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta), GiftPair('JohLen, 'JohLen)))
  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc), GiftPair('GeoHar, 'GeoHar)))
  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen), GiftPair('PauMcc, 'PauMcc)))

  private val playersExt: Players =
    Map('RinSta -> rinStaExt, 'JohLen -> johLenExt, 'GeoHar -> geoHarExt, 'PauMcc -> pauMccExt)

  "Players" should "return a player" in {
    assert(getPlayer(players, 'GeoHar) == Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))))
  }

  it should "return an updated players" in {
    assert(setPlayer(players, 'RinSta, Player("New Bee", Vector(GiftPair('NewBee, 'NewBee)))) == newBeePlayers)
  }

  it should "return an extended year players" in {
    assert(addYearPlayers(players) == playersExt)
  }

  it should "return a player's name" in {
    assert(getPlayerNamePlayers(players, 'GeoHar) == "George Harrison")
  }

  it should "return a givee" in {
    assert(getGiveePlayers(players, 'GeoHar, 0) == 'RinSta)
  }

  it should "return a giver" in {
    assert(getGiverPlayers(players, 'GeoHar, 0) == 'PauMcc)
  }
}
