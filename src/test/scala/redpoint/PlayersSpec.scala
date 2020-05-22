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

  private val newBee: Player = Player("New Bee", Vector(GiftPair(Symbol("NewBee"), Symbol("NewBee"))))
  private val newBeePlayers: Players =
    Map(Symbol("RinSta") -> newBee, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  "Players" should "return an updated player" in {
    assert(playersUpdatePlayer(players, Symbol("RinSta"), Player("New Bee", Vector(GiftPair(Symbol("NewBee"), Symbol("NewBee"))))) == newBeePlayers)
  }


  //  "Players" should "return a player's name" in {
  //    assert(playersGetPlayerName(players, Symbol("GeoHar")) == "George Harrison")
  //  }
  //



  //
  //  it should "return a playerSymbol("s name" in {
  //    assert(getPlayerNamePlayers(players, Symbol("GeoHar) == "George Harrison")
  //  }
  //

  //

}
