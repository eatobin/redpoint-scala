package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Players._

class PlayersSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  //  private val newBee: Player = Player("New Bee", Vector(GiftPair(Symbol("NewBee") Symbol("NewBee)))
  //  private val newBeePlayers: Players =
  //    Map(Symbol("RinSta -> newBee") Symbol("JohLen -> johLen") Symbol("GeoHar -> geoHar") Symbol("PauMcc -> pauMcc)
  //
  //  private val rinStaExt: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen") Symbol("GeoHar)") GiftPair(Symbol("RinSta") Symbol("RinSta)))
  //  private val johLenExt: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc") Symbol("RinSta)") GiftPair(Symbol("JohLen") Symbol("JohLen)))
  //  private val geoHarExt: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta") Symbol("PauMcc)") GiftPair(Symbol("GeoHar") Symbol("GeoHar)))
  //  private val pauMccExt: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar") Symbol("JohLen)") GiftPair(Symbol("PauMcc") Symbol("PauMcc)))
  //  private val playersExt: Players =
  //    Map(Symbol("RinSta -> rinStaExt, Symbol("JohLen -> johLenExt, Symbol("GeoHar -> geoHarExt, Symbol("PauMcc -> pauMccExt)
  //
  //  private val rinStaExt2: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen") Symbol("GeoHar)") GiftPair(Symbol("RinSta") Symbol("RinSta)") GiftPair(Symbol("RinSta") Symbol("RinSta)))
  //  private val johLenExt2: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc, Symbol("RinSta), GiftPair(Symbol("JohLen, Symbol("JohLen), GiftPair(Symbol("JohLen, Symbol("JohLen)))
  //  private val geoHarExt2: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta, Symbol("PauMcc), GiftPair(Symbol("GeoHar, Symbol("GeoHar), GiftPair(Symbol("GeoHar, Symbol("GeoHar)))
  //  private val pauMccExt2: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar, Symbol("JohLen), GiftPair(Symbol("PauMcc, Symbol("PauMcc), GiftPair(Symbol("PauMcc, Symbol("PauMcc)))
  //  private val playersExt2: Players =
  //    Map(Symbol("RinSta -> rinStaExt2, Symbol("JohLen -> johLenExt2, Symbol("GeoHar -> geoHarExt2, Symbol("PauMcc -> pauMccExt2)
  //
  //  private val geoHarGivee: Player = Player("George Harrison", Vector(GiftPair(Symbol("you, Symbol("PauMcc)))
  //  private val geoHarGiver: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta, Symbol("you)))
  //  private val playersGivee: Players =
  //    Map(Symbol("RinSta -> rinSta, Symbol("JohLen -> johLen, Symbol("GeoHar -> geoHarGivee, Symbol("PauMcc -> pauMcc)
  //  private val playersGiver: Players =
  //    Map(Symbol("RinSta -> rinSta, Symbol("JohLen -> johLen, Symbol("GeoHar -> geoHarGiver, Symbol("PauMcc -> pauMcc)

//  "Players" should "return a player's name" in {
//    assert(playersGetPlayerName(players, Symbol("GeoHar")) == "George Harrison")
//  }
//
//  it should "return a givee and a giver" in {
//    assert(playersGetGivee(players, Symbol("GeoHar"), 0) == Symbol("RinSta"))
//    assert(playersGetGiver(players, Symbol("GeoHar"), 0) == Symbol("PauMcc"))
//  }

  //  it should "return an updated players" in {
  //    assert(setPlayer(players, Symbol("RinSta, Player("New Bee", Vector(GiftPair(Symbol("NewBee, Symbol("NewBee)))) == newBeePlayers)
  //  }
  //
  //  it should "return an extended year players" in {
  //    assert(addYearPlayers(players) == playersExt)
  //    assert(addYearPlayers(playersExt) == playersExt2)
  //  }
  //
  //  it should "return a playerSymbol("s name" in {
  //    assert(getPlayerNamePlayers(players, Symbol("GeoHar) == "George Harrison")
  //  }
  //

  //
  //  it should "set a givEeEr" in {
  //    assert(setGivEeErPlayers(players, Symbol("GeoHar, 0, Symbol("you, Symbol("ee) == playersGivee)
  //    assert(setGivEeErPlayers(players, Symbol("GeoHar, 0, Symbol("you, Symbol("er) == playersGiver)
  //  }
}
