package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Roster._
import spray.json._

class RosterSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)
  private val roster: Roster = Roster("The Beatles", 2014, players)

  "A Roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "convert to JSON" in {
    val rosterJson: JsRoster = roster.toJson
    assert(rosterJson == """{"players":{"RinSta":{"giftHistory":[{"givee":"JohLen","giver":"GeoHar"}],"playerName":"Ringo Starr"},"JohLen":{"giftHistory":[{"givee":"PauMcc","giver":"RinSta"}],"playerName":"John Lennon"},"GeoHar":{"giftHistory":[{"givee":"RinSta","giver":"PauMcc"}],"playerName":"George Harrison"},"PauMcc":{"giftHistory":[{"givee":"GeoHar","giver":"JohLen"}],"playerName":"Paul McCartney"}},"rosterName":"The Beatles","rosterYear":2014}""".parseJson)
  }

  it should "convert from JSON" in {
    val rosterJson: JsRoster = roster.toJson
    assert(rosterJson.convertTo[Roster] == roster)
  }

  it should "convert a JSON string to a Roster" in {
    val rosterJson: JsRoster = roster.toJson
    assert(jsonStringToRoster(Right(rosterJson)) == Right(roster))
  }
}
