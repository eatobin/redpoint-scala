package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Roster._

class RosterSpec extends AnyFlatSpec {

  private val jsonStringRos: String = "{\"rosterName\":\"The Beatles\",\"rosterYear\":2014,\"players\":{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}}"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)
  private val roster: Roster = Roster("The Beatles", 2014, players)

  private val jsBeatlesBad: String = "{\"rosterName\"\"The Beatles\",\"rosterYear\":2014,\"players\":{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}}"

  "A Roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "convert from JSON - or not" in {
    val rosJson: Either[String, Roster] = rosterJsonStringToRoster(Right(jsonStringRos))
    val rosJsonBad: Either[String, Roster] = rosterJsonStringToRoster(Right(jsBeatlesBad))
    assert(rosJson == Right(roster))
    assert(rosJsonBad == Left(
      """Unexpected character '"' at input index 13 (line 1, position 14), expected ':':
{"rosterName""The Beatles","rosterYear":2014,"players":{"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]}}}
             ^
"""))
  }
}
