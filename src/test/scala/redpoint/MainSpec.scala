package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.{a, convertToAnyShouldWrapper}
import redpoint.Main._

class MainSpec extends AnyFlatSpec {

  private val filePath: String = "src/test/resources/beatles.json"
  private val badFilePath: String = "nope.json"
  private val badJsonFile: String = "src/test/resources/bad-json.json"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
  private val players: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)

  private val rinStaPlus: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))
  private val testHat: Set[String] = Set("RinSta")

  "Main" should "build a Roster" in {
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
    aDiscards = Hats.discardGivee(aDiscards, "GeoHar")
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
    assert(Players.playersGetGivee(aPlayers, giver, agYear) == givee)
    assert(Players.playersGetGiver(aPlayers, givee, agYear) == giver)
    assert(!ageHat.contains(givee))
  }

}
