package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Rules._

class RulesSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(giver = Symbol("KarLav"), givee = Symbol("JohLen"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(giver = Symbol("RinSta"), givee = Symbol("GeoHar"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(giver = Symbol("JohLen"), givee = Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(giver = Symbol("GeoHar"), givee = Symbol("EriTob"))))
  private val eriTob: Player = Player("Eric Tobin", Vector(GiftPair(giver = Symbol("PaulMcc"), givee = Symbol("KarLav"))))
  private val karLav: Player = Player("Karen Lavengood", Vector(GiftPair(giver = Symbol("EriTob"), givee = Symbol("RinSta"))))
  private val beatlesPlusPM: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc, Symbol("EriTob") -> eriTob, Symbol("KarLav") -> karLav)

  private var beatlesPlus4 = Players.addYear(beatlesPlusPM)
  beatlesPlus4 = Roster.updateGivee(beatlesPlus4, Symbol("RinSta"), 1, Symbol("GeoHar"))
  beatlesPlus4 = Players.addYear(beatlesPlus4)
  beatlesPlus4 = Roster.updateGivee(beatlesPlus4, Symbol("RinSta"), 2, Symbol("PauMcc"))
  beatlesPlus4 = Players.addYear(beatlesPlus4)
  beatlesPlus4 = Roster.updateGivee(beatlesPlus4, Symbol("RinSta"), 3, Symbol("EriTob"))
  beatlesPlus4 = Players.addYear(beatlesPlus4)
  beatlesPlus4 = Roster.updateGivee(beatlesPlus4, Symbol("RinSta"), 4, Symbol("KarLav"))

  "A Player" should "not give to itself" in {
    assert(giveeNotSelf(Symbol("RinSta"), Symbol("GeoHar")))
    assert(!giveeNotSelf(Symbol("RinSta"), Symbol("RinSta")))
  }

  it should "not give to it's recip" in {
    assert(giveeNotRecip(Symbol("RinSta"), Symbol("JohLen"), 0, beatlesPlusPM))
    assert(!giveeNotRecip(Symbol("RinSta"), Symbol("KarLav"), 0, beatlesPlusPM))
  }

  it should "not repeat for three years" in {
    assert(!giveeNotRepeat(Symbol("RinSta"), Symbol("JohLen"), 2, beatlesPlus4))
    assert(!giveeNotRepeat(Symbol("RinSta"), Symbol("GeoHar"), 2, beatlesPlus4))
    assert(giveeNotRepeat(Symbol("RinSta"), Symbol("KarLav"), 2, beatlesPlus4))
    assert(giveeNotRepeat(Symbol("RinSta"), Symbol("JohLen"), 5, beatlesPlus4))
    assert(giveeNotRepeat(Symbol("RinSta"), Symbol("GeoHar"), 5, beatlesPlus4))
    assert(!giveeNotRepeat(Symbol("RinSta"), Symbol("PauMcc"), 5, beatlesPlus4))
    assert(!giveeNotRepeat(Symbol("RinSta"), Symbol("EriTob"), 5, beatlesPlus4))
    assert(!giveeNotRepeat(Symbol("RinSta"), Symbol("KarLav"), 5, beatlesPlus4))
  }
}
