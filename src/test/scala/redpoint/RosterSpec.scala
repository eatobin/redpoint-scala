package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Roster._

class RosterSpec extends AnyFlatSpec {

  private val jsonStringRos: String = """{"players":{"RinSta":{"giftHistory":[{"givee":"JohLen","giver":"GeoHar"}],"playerName":"Ringo Starr"},"JohLen":{"giftHistory":[{"givee":"PauMcc","giver":"RinSta"}],"playerName":"John Lennon"},"GeoHar":{"giftHistory":[{"givee":"RinSta","giver":"PauMcc"}],"playerName":"George Harrison"},"PauMcc":{"giftHistory":[{"givee":"GeoHar","giver":"JohLen"}],"playerName":"Paul McCartney"}},"rosterName":"The Beatles","rosterYear":2014}"""

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Players =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)
  private val roster: Roster = Roster("The Beatles", 2014, players)

  private val jsBeatlesBad: String = """{  "roster-name": "The Beatles",  "roster-year": 2014,  "players": {    "RinSta": {      "player-name": "Ringo Starr",      "gift-history": [        {          "givee": "JohLen",          "giver": "GeoHar"        }      ]    }  },  "JohLen": {    "player-name": "John Lennon",    "gift-history": [      {        "givee": "PauMcc",        "giver": "RinSta"      }    ]  },  "GeoHar": {    "player-name": "George Harrison",    "gift-history": [      {        "givee": "RinSta",        "giver": "PauMcc"      }    ]  },  "PauMcc": {    "player-name": "Paul McCartney",    "gift-history": [      {        "givee": "GeoHar",        "giver": "JohLen"      }    ]  }}"""

  "A Roster" should "return \"The Beatles\" rosterName" in {
    assert(roster.rosterName == "The Beatles")
  }

  it should "return 2014 rosterYear" in {
    assert(roster.rosterYear == 2014)
  }

  it should "convert from JSON - or not" in {
    val rosJson: Either[ErrorString, Roster] = rosterJsonStringToRoster(Right(jsonStringRos))
    val rosJsonBad: Either[ErrorString, Roster] = rosterJsonStringToRoster(Right(jsBeatlesBad))
    val rosJsonNoFile: Either[ErrorString, Roster] = rosterJsonStringToRoster(Left("nope"))
    assert(rosJson == Right(roster))
    assert(rosJsonBad == Left("JSON parse error."))
    assert(rosJsonNoFile == Left("nope"))
  }
}
