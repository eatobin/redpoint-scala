package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import com.eatobin.redpointscala.Hat.Hat
import com.eatobin.redpointscala.Roster.ErrorString
import org.scalatest.flatspec.AnyFlatSpec

class RosterSpec extends AnyFlatSpec {

  private val jsonString: JsonString = "{  \"rosterName\": \"The Beatles\",  \"rosterYear\": 2014,  \"players\": {    \"PauMcc\": {      \"playerName\": \"Paul McCartney\",      \"giftHistory\": [        {          \"givee\": \"GeoHar\",          \"giver\": \"JohLen\"        }      ]    },    \"GeoHar\": {      \"playerName\": \"George Harrison\",      \"giftHistory\": [        {          \"givee\": \"RinSta\",          \"giver\": \"PauMcc\"        }      ]    },    \"JohLen\": {      \"playerName\": \"John Lennon\",      \"giftHistory\": [        {          \"givee\": \"PauMcc\",          \"giver\": \"RinSta\"        }      ]    },    \"RinSta\": {      \"playerName\": \"Ringo Starr\",      \"giftHistory\": [        {          \"givee\": \"JohLen\",          \"giver\": \"GeoHar\"        }      ]    }  },  \"giftYear\": 0,  \"giveeHat\": null,  \"giverHat\": null,  \"maybeGivee\": null,  \"maybeGiver\": null,  \"discards\": null}"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
  private val players: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)
  private val emptyHat: Hat = Set()
  private val roster: Roster = Roster("The Beatles", 2014, players, 0, emptyHat, emptyHat, None, None, emptyHat)
//
//  private val jsBeatlesBad: JsonString = "{\"rosterName\"\"The Beatles\",\"rosterYear\":2014,\"players\":{\"PauMcc\":{\"playerName\":\"Paul McCartney\",\"giftHistory\":[{\"givee\":\"GeoHar\",\"giver\":\"JohLen\"}]},\"GeoHar\":{\"playerName\":\"George Harrison\",\"giftHistory\":[{\"givee\":\"RinSta\",\"giver\":\"PauMcc\"}]},\"JohLen\":{\"playerName\":\"John Lennon\",\"giftHistory\":[{\"givee\":\"PauMcc\",\"giver\":\"RinSta\"}]},\"RinSta\":{\"playerName\":\"Ringo Starr\",\"giftHistory\":[{\"givee\":\"JohLen\",\"giver\":\"GeoHar\"}]}}}"
//
//
//  "A Roster" should "return \"The Beatles\" rosterName" in {
//    assert(roster.rosterName == "The Beatles")
//  }
//
//  it should "return 2014 rosterYear" in {
//    assert(roster.rosterYear == 2014)
//  }
//
  "A Roster" should "convert from JSON - or not" in {
    val rosJson: Either[ErrorString, Roster] = Roster.rosterJsonStringToRoster(Right(jsonString))
//    val rosJsonBad: Either[ErrorString, Roster] = Roster.rosterJsonStringToRoster(Right(jsonString))
    val rosNoFile: Either[ErrorString, Roster] = Roster.rosterJsonStringToRoster(Left("Just made this up"))
    assert(rosJson == Right(roster))
//    assert(rosJsonBad == Left("""io.circe.ParsingFailure: expected : got '"The B...' (line 1, column 14)"""))
//    assert(rosNoFile == Left("Just made this up"))
  }
}
