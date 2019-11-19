package redpoint

import org.scalatest.FlatSpec
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

  "A Player" should "not give to itself" in {
    assert(giveeNotSelf('RinSta, 'GeoHar))
    assert(!giveeNotSelf('RinSta, 'RinSta))
  }

  it should "not give to it's recip" in {
    assert(giveeNotRecip('RinSta, 'JohLen, 0, beatlesPlusPM))
    assert(!giveeNotRecip('RinSta, 'KarLav, 0, beatlesPlusPM))
  }

  //  it should "return an updated giftHistory" in {
  //    assert(setGiftHistory(player, Vector(GiftPair('nope, 'yup))) ==
  //      Player("Ringo Starr", Vector(GiftPair('nope, 'yup))))
  //  }
  //
  //  it should "return an extended giftHistory in player" in {
  //    assert(addYearPlayer(player, 'mee) ==
  //      Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar), GiftPair('mee, 'mee))))
  //  }
}
