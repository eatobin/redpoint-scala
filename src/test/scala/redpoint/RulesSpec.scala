package redpoint

import org.scalatest.FlatSpec
import redpoint.Rules._

class RulesSpec extends FlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair('KarLav, 'JohLen)))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair('RinSta, 'GeoHar)))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair('JohLen, 'PauMcc)))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'EriTob)))
  private val eriTob: Player = Player("Eric Tobin", Vector(GiftPair('PaulMcc, 'KarLav)))
  private val karLav: Player = Player("Karen Lavengood", Vector(GiftPair('EriTob, 'RinSta)))
  private val beatlesPlusPM: Players =
    Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc, 'EriTob -> eriTob, 'KarLav -> karLav)

  "A Player" should "not give to itself" in {
    assert(giveeNotSelf('RinSta, 'GeoHar) == true)
  }

  //  it should "return its giftHistory" in {
  //    assert(player.giftHistory == Vector(GiftPair('JohLen, 'GeoHar)))
  //  }
  //
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
