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

  private val jsBeatlesBad: JsRoster = """{  "roster-name": "The Beatles",  "roster-year": 2014,  "players": {    "RinSta": {      "player-name": "Ringo Starr",      "gift-history": [        {          "givee": "JohLen",          "giver": "GeoHar"        }      ]    }  },  "JohLen": {    "player-name": "John Lennon",    "gift-history": [      {        "givee": "PauMcc",        "giver": "RinSta"      }    ]  },  "GeoHar": {    "player-name": "George Harrison",    "gift-history": [      {        "givee": "RinSta",        "giver": "PauMcc"      }    ]  },  "PauMcc": {    "player-name": "Paul McCartney",    "gift-history": [      {        "givee": "GeoHar",        "giver": "JohLen"      }    ]  }}""".parseJson

  "A Roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "convert a JSON string to a Roster - or not" in {
    val rosterJson: JsRoster = roster.toJson
    assert(jsonStringToRoster(Right(rosterJson)) == Right(roster))
    assert(jsonStringToRoster(Right(jsBeatlesBad)) == Left("JSON parse error."))
    assert(jsonStringToRoster(Left("nope")) == Left("nope"))
  }
}
