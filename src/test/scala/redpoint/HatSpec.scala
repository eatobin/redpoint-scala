package redpoint

import org.scalatest.FlatSpec
import redpoint.Hats._

class HatSpec extends FlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar)))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta)))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc)))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))

  private val players: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

  private val testHat: Hat = Set('RinSta, 'JohLen, 'GeoHar, 'PauMcc)

  "A Hat" should "make itself given players" in {
    assert(makeHat(players) == testHat)
  }

  it should "remove a puck" in {
    assert(removePuck(testHat, 'RinSta) == Set('JohLen, 'GeoHar, 'PauMcc))
    assert(removePuck(Set(), 'RinSta) == Set())
  }

  it should "return discarded givees" in {
    assert(returnDiscards(Set('PauMcc, 'JohLen), Set('GeoHar)) == Set('JohLen, 'PauMcc, 'GeoHar))
  }
}
