package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Hats._

class HatSpec extends AnyFlatSpec {

  private val testHat: Hat = Set(Symbol("RinSta"), Symbol("JohLen"), Symbol("GeoHar"), Symbol("PauMcc"))

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))

  private val players: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)


  "A Hat" should "make itself given players" in {
    assert(makeHat(players) == testHat)
  }

  it should "remove a puck" in {
    assert(removePuck(testHat, Symbol("RinSta")) == Set(Symbol("JohLen"), Symbol("GeoHar"), Symbol("PauMcc")))
    assert(removePuck(Set(), Symbol("RinStaX")) == Set())
  }

  it should "discard a puck" in {
    assert(discardGivee(Set(Symbol("PauMcc")), Symbol("JohLen")) == Set(Symbol("PauMcc"), Symbol("JohLen")))
  }

  it should "return discarded givees" in {
    assert(returnDiscards(Set(Symbol("PauMcc"), Symbol("JohLen")), Set(Symbol("GeoHar"))) == Set(Symbol("JohLen"), Symbol("PauMcc"), Symbol("GeoHar")))
  }
}
