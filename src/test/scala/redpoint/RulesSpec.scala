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

  //  private var beatlesPlus4 = addYearPlayers(beatlesPlusPM)
  //  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 1, 'GeoHar, 'ee)
  //  beatlesPlus4 = addYearPlayers(beatlesPlus4)
  //  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 2, 'PauMcc, 'ee)
  //  beatlesPlus4 = addYearPlayers(beatlesPlus4)
  //  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 3, 'EriTob, 'ee)
  //  beatlesPlus4 = addYearPlayers(beatlesPlus4)
  //  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 4, 'KarLav, 'ee)

  "A Player" should "not give to itself" in {
    assert(giveeNotSelf(Symbol("RinSta"), Symbol("GeoHar")))
    assert(!giveeNotSelf(Symbol("RinSta"), Symbol("RinSta")))
  }

  //  it should "not give to it's recip" in {
  //    assert(giveeNotRecip('RinSta, 'JohLen, 0, beatlesPlusPM))
  //    assert(!giveeNotRecip('RinSta, 'KarLav, 0, beatlesPlusPM))
  //  }
  //
  //  it should "not repeat for three years" in {
  //    assert(!giveeNotRepeat('RinSta, 'JohLen, 2, beatlesPlus4))
  //    assert(!giveeNotRepeat('RinSta, 'GeoHar, 2, beatlesPlus4))
  //    assert(giveeNotRepeat('RinSta, 'KarLav, 2, beatlesPlus4))
  //    assert(giveeNotRepeat('RinSta, 'JohLen, 5, beatlesPlus4))
  //    assert(giveeNotRepeat('RinSta, 'GeoHar, 5, beatlesPlus4))
  //    assert(!giveeNotRepeat('RinSta, 'PauMcc, 5, beatlesPlus4))
  //    assert(!giveeNotRepeat('RinSta, 'EriTob, 5, beatlesPlus4))
  //    assert(!giveeNotRepeat('RinSta, 'KarLav, 5, beatlesPlus4))
  //  }
}
