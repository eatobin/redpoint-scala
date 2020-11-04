package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Main._

class MainSpec extends AnyFlatSpec {

  private val fp = "src/test/resources/beatles.json"

  private val jsonStringRos: String = "{  \"rosterName\": \"The Beatles\",  \"rosterYear\": 2014,  \"players\": {    \"PauMcc\": {      \"playerName\": \"Paul McCartney\",      \"giftHistory\": [        {          \"givee\": \"GeoHar\",          \"giver\": \"JohLen\"        }      ]    },    \"GeoHar\": {      \"playerName\": \"George Harrison\",      \"giftHistory\": [        {          \"givee\": \"RinSta\",          \"giver\": \"PauMcc\"        }      ]    },    \"JohLen\": {      \"playerName\": \"John Lennon\",      \"giftHistory\": [        {          \"givee\": \"PauMcc\",          \"giver\": \"RinSta\"        }      ]    },    \"RinSta\": {      \"playerName\": \"Ringo Starr\",      \"giftHistory\": [        {          \"givee\": \"JohLen\",          \"giver\": \"GeoHar\"        }      ]    }  }}"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  private val testHat: Set[Symbol] = Set(Symbol("RinSta"))

  "Main" should "return a Roster as a String" in {
    assert(readFileIntoJsonString(fp) == Right(jsonStringRos))
  }

  it should "build a Roster" in {
    rosterOrQuit(fp)
    assert(aRosterName == "The Beatles")
    assert(aRosterYear == 2014)
    assert(aPlayers == players)
  }

  it should "draw a puck" in {
    assert(drawPuck(testHat).contains(Symbol("RinSta")))
    assert(drawPuck(Set()).isEmpty)
  }
}
