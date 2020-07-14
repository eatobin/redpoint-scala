package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Main._
import spray.json._

class MainSpec extends AnyFlatSpec {

  private val fp = "src/test/resources/beatles.json"

  "Main" should "return return a Roster as a String" in {
    assert(rosterOrQuit(fp) == Right("""{  "roster-name": "The Beatles",  "roster-year": 2014,  "players": {    "RinSta": {      "player-name": "Ringo Starr",      "gift-history": [        {          "givee": "JohLen",          "giver": "GeoHar"        }      ]    }  },  "JohLen": {    "player-name": "John Lennon",    "gift-history": [      {        "givee": "PauMcc",        "giver": "RinSta"      }    ]  },  "GeoHar": {    "player-name": "George Harrison",    "gift-history": [      {        "givee": "RinSta",        "giver": "PauMcc"      }    ]  },  "PauMcc": {    "player-name": "Paul McCartney",    "gift-history": [      {        "givee": "GeoHar",        "giver": "JohLen"      }    ]  }}"""))
  }
}
