package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Main._
import spray.json._

class MainSpec extends AnyFlatSpec {

  private val fp = "src/test/resources/beatles.json"
  private val jsBeatles: JsValue = """{  "roster-name": "The Beatles",  "roster-year": 2014,  "players": {    "RinSta": {      "player-name": "Ringo Starr",      "gift-history": [        {          "givee": "JohLen",          "giver": "GeoHar"        }      ]    }  },  "JohLen": {    "player-name": "John Lennon",    "gift-history": [      {        "givee": "PauMcc",        "giver": "RinSta"      }    ]  },  "GeoHar": {    "player-name": "George Harrison",    "gift-history": [      {        "givee": "RinSta",        "giver": "PauMcc"      }    ]  },  "PauMcc": {    "player-name": "Paul McCartney",    "gift-history": [      {        "givee": "GeoHar",        "giver": "JohLen"      }    ]  }}""".parseJson
  private val testHat: Hat = Set(Symbol("RinSta"))

  "Main" should "return a Roster as a String" in {
    assert(readFileIntoJsValue(fp) == Right(jsBeatles))
    assert(readFileIntoJsValue("src/test/resources/none.json") == Left("File read error. File: " ++ "src/test/resources/none.json" ++ " does not exist."))
  }

  it should "draw a puck" in {
    assert(drawPuck(testHat).contains(Symbol("RinSta")))
    assert(drawPuck(Set()).isEmpty)
  }
}
