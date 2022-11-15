package com.eatobin.redpointscala

import org.scalatest.flatspec.AnyFlatSpec

class HelpersSpec extends AnyFlatSpec {

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

  //  "Redpoint" should "build a Roster" in {
  //    helpersRosterOrQuit(filePath)
  //    assert(aRosterName == "The Beatles")
  //    assert(aRosterYear == 2014)
  //    assert(aPlayers == players)
  //  }
  //
  //  it should "detect a bad file path" in {
  //    helpersRosterOrQuit(badFilePath) shouldBe a[Unit]
  //  }
  //
  //  it should "detect a bad JSON parse" in {
  //    helpersRosterOrQuit(badJsonFile) shouldBe a[Unit]
  //  }
  //
  //  it should "draw a puck" in {
  //    assert(helpersDrawPuck(testHat).contains("RinSta"))
  //    assert(helpersDrawPuck(Set()).isEmpty)
  //  }
  //
  //  it should "start a new year" in {
  //    aGiftYear = 0
  //    aMaybeGiver = None
  //    aMaybeGivee = None
  //    helpersRosterOrQuit(filePath)
  //    helpersStartNewYear()
  //    assert(aGiftYear == 1)
  //    assert(aMaybeGiver.isDefined)
  //    assert(aMaybeGivee.isDefined)
  //    assert(rinStaPlus == aPlayers("RinSta"))
  //    assert(aDiscards.isEmpty)
  //  }
  //
  //  it should "select a new giver" in {
  //    aGiftYear = 0
  //    aMaybeGiver = None
  //    aMaybeGivee = None
  //    helpersRosterOrQuit(filePath)
  //    helpersStartNewYear()
  //    aDiscards = Hat.hatDiscardGivee("GeoHar", aDiscards)
  //    assert(aDiscards.size == 1)
  //    helpersSelectNewGiver()
  //    assert(aGiverHat.size == 3)
  //    assert(aDiscards.isEmpty)
  //  }
  //
  //  it should "have a successful givee" in {
  //    aGiftYear = 0
  //    aMaybeGiver = None
  //    aMaybeGivee = None
  //    helpersRosterOrQuit(filePath)
  //    helpersStartNewYear()
  //    val givee = aMaybeGivee.get
  //    val giver = aMaybeGiver.get
  //    helpersGiveeIsSuccess()
  //    assert(Players.playersGetMyGivee(giver)(aGiftYear)(aPlayers) == givee)
  //    assert(Players.playersGetMyGiver(givee)(aGiftYear)(aPlayers) == giver)
  //    assert(!aGiveeHat.contains(givee))
  //  }
  //
  //  it should "have a failing givee" in {
  //    aGiftYear = 0
  //    aMaybeGiver = None
  //    aMaybeGivee = None
  //    helpersRosterOrQuit(filePath)
  //    helpersStartNewYear()
  //    val givee = aMaybeGivee.get
  //    helpersGiveeIsFailure()
  //    assert(aDiscards.contains(givee))
  //    assert(!aGiveeHat.contains(givee))
  //  }
  //
  //  it should "report player errors" in {
  //    aGiftYear = 0
  //    aPlayers = playersWeird
  //    assert(helpersErrors() == Seq("GeoHar", "PauMcc"))
  //  }
  //
  //  it should "print" in {
  //    aGiftYear = 0
  //    helpersRosterOrQuit(filePath)
  //    helpersPrintStringGivingRoster("The Beatles")(2021)
  //
  //    aPlayers = playersWeird
  //    helpersPrintStringGivingRoster("The Weird Beatles")(2050)
  //  }
}
