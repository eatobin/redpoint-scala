package redpoint

import org.scalatest.FlatSpec
import redpoint.Players.{addYearPlayers, setGivEeErPlayers}
import redpoint.Rules._

class RulesSpec extends FlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(giver = 'KarLav, givee = 'JohLen)))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(giver = 'RinSta, givee = 'GeoHar)))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(giver = 'JohLen, givee = 'PauMcc)))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(giver = 'GeoHar, givee = 'EriTob)))
  private val eriTob: Player = Player("Eric Tobin", Vector(GiftPair(giver = 'PaulMcc, givee = 'KarLav)))
  private val karLav: Player = Player("Karen Lavengood", Vector(GiftPair(giver = 'EriTob, givee = 'RinSta)))
  private val beatlesPlusPM: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc, 'EriTob -> eriTob, 'KarLav -> karLav)

  private val extended = addYearPlayers(addYearPlayers(addYearPlayers(addYearPlayers(beatlesPlusPM))))
  private var beatlesPlus4 = setGivEeErPlayers(extended, 'RinSta, 1, 'GeoHar, 'ee)
  beatlesPlus4 = setGivEeErPlayers(extended, 'RinSta, 2, 'PauMcc, 'ee)
  beatlesPlus4 = setGivEeErPlayers(extended, 'RinSta, 3, 'EriTob, 'ee)
  beatlesPlus4 = setGivEeErPlayers(extended, 'RinSta, 4, 'KarLav, 'ee)

  "A Player" should "not give to itself" in {
    assert(giveeNotSelf('RinSta, 'GeoHar))
    assert(!giveeNotSelf('RinSta, 'RinSta))
  }

  it should "not give to it's recip" in {
    assert(giveeNotRecip('RinSta, 'JohLen, 0, beatlesPlusPM))
    assert(!giveeNotRecip('RinSta, 'KarLav, 0, beatlesPlusPM))
  }

  it should "not repeat for three years" in {
    assert(!giveeNotRepeat('RinSta, 'JohLen, 2, beatlesPlus4))
  }
}
