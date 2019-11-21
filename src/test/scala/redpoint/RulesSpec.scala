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

  private var beatlesPlus4 = addYearPlayers(beatlesPlusPM)
  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 1, 'GeoHar, 'ee)
  beatlesPlus4 = addYearPlayers(beatlesPlus4)
  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 2, 'PauMcc, 'ee)
  beatlesPlus4 = addYearPlayers(beatlesPlus4)
  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 3, 'EriTob, 'ee)
  beatlesPlus4 = addYearPlayers(beatlesPlus4)
  beatlesPlus4 = setGivEeErPlayers(beatlesPlus4, 'RinSta, 4, 'KarLav, 'ee)

  //  TODO
  //  clojureBeatlesPlus4 =
  //  scalaBeatlesPlus4 = Map('PauMcc -> Player(Paul McCartney,Vector(GiftPair('EriTob,'GeoHar), GiftPair('PauMcc,'PauMcc), GiftPair('PauMcc,'PauMcc), GiftPair('PauMcc,'PauMcc), GiftPair('PauMcc,'PauMcc))), 'GeoHar -> Player(George Harrison,Vector(GiftPair('PauMcc,'JohLen), GiftPair('GeoHar,'GeoHar), GiftPair('GeoHar,'GeoHar), GiftPair('GeoHar,'GeoHar), GiftPair('GeoHar,'GeoHar))), 'KarLav -> Player(Karen Lavengood,Vector(GiftPair('RinSta,'EriTob), GiftPair('KarLav,'KarLav), GiftPair('KarLav,'KarLav), GiftPair('KarLav,'KarLav), GiftPair('KarLav,'KarLav))), 'RinSta -> Player(Ringo Starr,Vector(GiftPair('JohLen,'KarLav), GiftPair('GeoHar,'RinSta), GiftPair('PauMcc,'RinSta), GiftPair('EriTob,'RinSta), GiftPair('KarLav,'RinSta))), 'JohLen -> Player(John Lennon,Vector(GiftPair('GeoHar,'RinSta), GiftPair('JohLen,'JohLen), GiftPair('JohLen,'JohLen), GiftPair('JohLen,'JohLen), GiftPair('JohLen,'JohLen))), 'EriTob -> Player(Eric Tobin,Vector(GiftPair('KarLav,'PaulMcc), GiftPair('EriTob,'EriTob), GiftPair('EriTob,'EriTob), GiftPair('EriTob,'EriTob), GiftPair('EriTob,'EriTob))))

  //  (deftest givee-not-repeat-test
  //  (is (= false
  //    (rule/givee-not-repeat? :RinSta :JohLen 2 beatles-plus-4)))
  //  (is (= false
  //    (rule/givee-not-repeat? :RinSta :GeoHar 2 beatles-plus-4)))
  //  (is (= true
  //    (rule/givee-not-repeat? :RinSta :KarLav 2 beatles-plus-4)))
  //  (is (= true
  //    (rule/givee-not-repeat? :RinSta :JohLen 5 beatles-plus-4)))
  //  (is (= true
  //    (rule/givee-not-repeat? :RinSta :GeoHar 5 beatles-plus-4)))
  //  (is (= false
  //    (rule/givee-not-repeat? :RinSta :PauMcc 5 beatles-plus-4)))
  //  (is (= false
  //    (rule/givee-not-repeat? :RinSta :EriTob 5 beatles-plus-4)))
  //  (is (= false
  //    (rule/givee-not-repeat? :RinSta :KarLav 5 beatles-plus-4))))

  "A Player" should "not give to itself" in {
    assert(giveeNotSelf('RinSta, 'GeoHar))
    assert(!giveeNotSelf('RinSta, 'RinSta))
  }

  it should "not give to it's recip" in {
    assert(giveeNotRecip('RinSta, 'JohLen, 0, beatlesPlusPM))
    assert(!giveeNotRecip('RinSta, 'KarLav, 0, beatlesPlusPM))
  }

  it should "not repeat for three years" in {
    println(beatlesPlus4)
    //    assert(giveeNotRepeat('RinSta, 'JohLen, 2, beatlesPlus4))
    //    assert(!giveeNotRepeat('RinSta, 'PauMcc, 2, beatlesPlus4))
  }
}
