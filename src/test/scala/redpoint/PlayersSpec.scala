package redpoint

import org.scalatest.FlatSpec
import redpoint.Players._

class PlayersSpec extends FlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar)))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta)))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc)))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))
  private val players: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

  private val newBee: Player = Player("New Bee", Vector(GiftPair('NewBee, 'NewBee)))
  private val newBeePlayers: Players =
    Map('RinSta -> newBee, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar), GiftPair('RinSta, 'RinSta)))
  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta), GiftPair('JohLen, 'JohLen)))
  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc), GiftPair('GeoHar, 'GeoHar)))
  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen), GiftPair('PauMcc, 'PauMcc)))
  private val playersExt: Players =
    Map('RinSta -> rinStaExt, 'JohLen -> johLenExt, 'GeoHar -> geoHarExt, 'PauMcc -> pauMccExt)

  private val rinStaExt2: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar), GiftPair('RinSta, 'RinSta), GiftPair('RinSta, 'RinSta)))
  private val johLenExt2: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta), GiftPair('JohLen, 'JohLen), GiftPair('JohLen, 'JohLen)))
  private val geoHarExt2: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc), GiftPair('GeoHar, 'GeoHar), GiftPair('GeoHar, 'GeoHar)))
  private val pauMccExt2: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen), GiftPair('PauMcc, 'PauMcc), GiftPair('PauMcc, 'PauMcc)))
  private val playersExt2: Players =
    Map('RinSta -> rinStaExt2, 'JohLen -> johLenExt2, 'GeoHar -> geoHarExt2, 'PauMcc -> pauMccExt2)

  private val geoHarGivee: Player = Player("George Harrison", Vector(GiftPair('you, 'PauMcc)))
  private val geoHarGiver: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'you)))
  private val playersGivee: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHarGivee, 'PauMcc -> pauMcc)
  private val playersGiver: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHarGiver, 'PauMcc -> pauMcc)

  "Players" should "return a player" in {
    assert(getPlayer(players, 'GeoHar) == Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))))
  }

  it should "return an updated players" in {
    assert(setPlayer(players, 'RinSta, Player("New Bee", Vector(GiftPair('NewBee, 'NewBee)))) == newBeePlayers)
  }

  it should "return an extended year players" in {
    assert(addYearPlayers(players) == playersExt)
    assert(addYearPlayers(playersExt) == playersExt2)
  }

  it should "return a player's name" in {
    assert(getPlayerNamePlayers(players, 'GeoHar) == "George Harrison")
  }

  it should "return a givEeEr" in {
    assert(getGivEeErPlayers(players, 'GeoHar, 'ee, 0) == 'RinSta)
    assert(getGivEeErPlayers(players, 'GeoHar, 'er, 0) == 'PauMcc)
  }

  it should "set a givEeEr" in {
    assert(setGivEeErPlayers(players, 'GeoHar, 0, 'you, 'ee) == playersGivee)
    assert(setGivEeErPlayers(players, 'GeoHar, 0, 'you, 'er) == playersGiver)
  }
}
