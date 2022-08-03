package com.eatobin.redpointscala

import com.eatobin.redpointscala.Redpoint._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.{a, convertToAnyShouldWrapper}

class RedpointSpec extends AnyFlatSpec {

  private val filePath: String = "src/test/resources/beatles.json"
  private val badFilePath: String = "nope.json"
  private val badJsonFile: String = "src/test/resources/bad-json.json"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
  private val players: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)

  private val geoWhoops: Player = Player("George Harrison", Vector(GiftPair("GeoHar", "PauMcc")))
  private val pauYikes: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "PauMcc")))
  private val playersWeird: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoWhoops, "PauMcc" -> pauYikes)

  private val rinStaPlus: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
  private val testHat: Set[String] = Set("RinSta")

  "Redpoint" should "build a Roster" in {
    rosterOrQuit(filePath)
    assert(aRosterName == "The Beatles")
    assert(aRosterYear == 2014)
    assert(aPlayers == players)
  }

  it should "detect a bad file path" in {
    rosterOrQuit(badFilePath) shouldBe a[Unit]
  }

  it should "detect a bad JSON parse" in {
    rosterOrQuit(badJsonFile) shouldBe a[Unit]
  }

  it should "draw a puck" in {
    assert(drawPuck(testHat).contains("RinSta"))
    assert(drawPuck(Set()).isEmpty)
  }

  it should "start a new year" in {
    agYear = 0
    maybeGiver = None
    maybeGivee = None
    rosterOrQuit(filePath)
    startNewYear()
    assert(agYear == 1)
    assert(maybeGiver.isDefined)
    assert(maybeGivee.isDefined)
    assert(rinStaPlus == aPlayers("RinSta"))
    assert(aDiscards.isEmpty)
  }

  it should "select a new giver" in {
    agYear = 0
    maybeGiver = None
    maybeGivee = None
    rosterOrQuit(filePath)
    startNewYear()
    aDiscards = Hats.discardGivee("GeoHar", aDiscards)
    assert(aDiscards.size == 1)
    selectNewGiver()
    assert(agrHat.size == 3)
    assert(aDiscards.isEmpty)
  }

  it should "have a successful givee" in {
    agYear = 0
    maybeGiver = None
    maybeGivee = None
    rosterOrQuit(filePath)
    startNewYear()
    val givee = maybeGivee.get
    val giver = maybeGiver.get
    giveeIsSuccess()
    assert(Players.playersGetGivee(giver, agYear, aPlayers) == givee)
    assert(Players.playersGetGiver(givee, agYear, aPlayers) == giver)
    assert(!ageHat.contains(givee))
  }

  it should "have a failing givee" in {
    agYear = 0
    maybeGiver = None
    maybeGivee = None
    rosterOrQuit(filePath)
    startNewYear()
    val givee = maybeGivee.get
    giveeIsFailure()
    assert(aDiscards.contains(givee))
    assert(!ageHat.contains(givee))
  }

  it should "report player errors" in {
    agYear = 0
    aPlayers = playersWeird
    assert(errors() == Seq("GeoHar", "PauMcc"))
  }

  it should "print" in {
    agYear = 0
    rosterOrQuit(filePath)
    printStringGivingRoster("The Beatles", 2021)

    aPlayers = playersWeird
    printStringGivingRoster("The Weird Beatles", 2050)
  }
}
