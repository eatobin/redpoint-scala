package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.{a, shouldBe}

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
    Redpoint.rosterOrQuit(filePath)
    assert(Redpoint.aRosterName == "The Beatles")
    assert(Redpoint.aRosterYear == 2014)
    assert(Redpoint.aPlayers == players)
  }

  it should "detect a bad file path" in {
    Redpoint.rosterOrQuit(badFilePath) shouldBe a[Unit]
  }

  it should "detect a bad JSON parse" in {
    Redpoint.rosterOrQuit(badJsonFile) shouldBe a[Unit]
  }

  it should "draw a puck" in {
    assert(Redpoint.drawPuck(testHat).contains("RinSta"))
    assert(Redpoint.drawPuck(Set()).isEmpty)
  }

  it should "start a new year" in {
    Redpoint.agYear = 0
    Redpoint.maybeGiver = None
    Redpoint.maybeGivee = None
    Redpoint.rosterOrQuit(filePath)
    Redpoint.startNewYear()
    assert(Redpoint.agYear == 1)
    assert(Redpoint.maybeGiver.isDefined)
    assert(Redpoint.maybeGivee.isDefined)
    assert(rinStaPlus == Redpoint.aPlayers("RinSta"))
    assert(Redpoint.aDiscards.isEmpty)
  }

  it should "select a new giver" in {
    Redpoint.agYear = 0
    Redpoint.maybeGiver = None
    Redpoint.maybeGivee = None
    Redpoint.rosterOrQuit(filePath)
    Redpoint.startNewYear()
    Redpoint.aDiscards = Hats.discardGivee("GeoHar")(Redpoint.aDiscards)
    assert(Redpoint.aDiscards.size == 1)
    Redpoint.selectNewGiver()
    assert(Redpoint.agrHat.size == 3)
    assert(Redpoint.aDiscards.isEmpty)
  }

  it should "have a successful givee" in {
    Redpoint.agYear = 0
    Redpoint.maybeGiver = None
    Redpoint.maybeGivee = None
    Redpoint.rosterOrQuit(filePath)
    Redpoint.startNewYear()
    val givee = Redpoint.maybeGivee.get
    val giver = Redpoint.maybeGiver.get
    Redpoint.giveeIsSuccess()
    assert(Players.getGivee(giver)(Redpoint.agYear)(Redpoint.aPlayers) == givee)
    assert(Players.getGiver(givee)(Redpoint.agYear)(Redpoint.aPlayers) == giver)
    assert(!Redpoint.ageHat.contains(givee))
  }

  it should "have a failing givee" in {
    Redpoint.agYear = 0
    Redpoint.maybeGiver = None
    Redpoint.maybeGivee = None
    Redpoint.rosterOrQuit(filePath)
    Redpoint.startNewYear()
    val givee = Redpoint.maybeGivee.get
    Redpoint.giveeIsFailure()
    assert(Redpoint.aDiscards.contains(givee))
    assert(!Redpoint.ageHat.contains(givee))
  }

  it should "report player errors" in {
    Redpoint.agYear = 0
    Redpoint.aPlayers = playersWeird
    assert(Redpoint.errors() == Seq("GeoHar", "PauMcc"))
  }

  it should "print" in {
    Redpoint.agYear = 0
    Redpoint.rosterOrQuit(filePath)
    Redpoint.printStringGivingRoster("The Beatles")(2021)

    Redpoint.aPlayers = playersWeird
    Redpoint.printStringGivingRoster("The Weird Beatles")(2050)
  }
}
