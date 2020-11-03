package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Main._

class MainSpec extends AnyFlatSpec {

  private val fp = "src/test/resources/beatles.json"
  private val jsonStringRos: String = "{  \"rosterName\": \"The Beatles\",  \"rosterYear\": 2014,  \"players\": {    \"PauMcc\": {      \"playerName\": \"Paul McCartney\",      \"giftHistory\": [        {          \"givee\": \"GeoHar\",          \"giver\": \"JohLen\"        }      ]    },    \"GeoHar\": {      \"playerName\": \"George Harrison\",      \"giftHistory\": [        {          \"givee\": \"RinSta\",          \"giver\": \"PauMcc\"        }      ]    },    \"JohLen\": {      \"playerName\": \"John Lennon\",      \"giftHistory\": [        {          \"givee\": \"PauMcc\",          \"giver\": \"RinSta\"        }      ]    },    \"RinSta\": {      \"playerName\": \"Ringo Starr\",      \"giftHistory\": [        {          \"givee\": \"JohLen\",          \"giver\": \"GeoHar\"        }      ]    }  }}"
  private val testHat: Set[Symbol] = Set(Symbol("RinSta"))

  "Main" should "return a Roster as a String" in {
    assert(readFileIntoJsonString(fp) == Right(jsonStringRos))
  }

  it should "draw a puck" in {
    assert(drawPuck(testHat).contains(Symbol("RinSta")))
    assert(drawPuck(Set()).isEmpty)
  }
}
