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

  "Players" should "return a player" in {
    assert(getPlayer(players, 'GeoHar) == Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc))))
  }

  it should "return an updated players" in {
    assert(setPlayer(players, 'RinSta, Player("New Bee", Vector(GiftPair('NewBee, 'NewBee)))) == newBeePlayers)
  }
}
