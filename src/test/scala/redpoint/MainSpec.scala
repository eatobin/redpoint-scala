package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.{a, convertToAnyShouldWrapper}

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
    Main.rosterOrQuit(filePath)
    assert(Main.aRosterName == "The Beatles")
    assert(Main.aRosterYear == 2014)
    assert(Main.aPlayers == players)
  }

  it should "detect a bad file path" in {
    Main.rosterOrQuit(badFilePath) shouldBe a[Unit]
  }

  it should "detect a bad JSON parse" in {
    Main.rosterOrQuit(badJsonFile) shouldBe a[Unit]
  }

  it should "draw a puck" in {
    assert(Main.drawPuck(testHat).contains("RinSta"))
    assert(Main.drawPuck(Set()).isEmpty)
  }

  it should "start a new year" in {
    Main.agYear = 0
    Main.maybeGiver = None
    Main.maybeGivee = None
    Main.rosterOrQuit(filePath)
    Main.startNewYear()
    assert(Main.agYear == 1)
    assert(Main.maybeGiver.isDefined)
    assert(Main.maybeGivee.isDefined)
    assert(rinStaPlus == Main.aPlayers("RinSta"))
    assert(Main.aDiscards.isEmpty)
  }

  it should "select a new giver" in {
    Main.agYear = 0
    Main.maybeGiver = None
    Main.maybeGivee = None
    Main.rosterOrQuit(filePath)
    Main.startNewYear()
    Main.aDiscards = Hats.discardGivee("GeoHar")(Main.aDiscards)
    assert(Main.aDiscards.size == 1)
    Main.selectNewGiver()
    assert(Main.agrHat.size == 3)
    assert(Main.aDiscards.isEmpty)
  }

  it should "have a successful givee" in {
    Main.agYear = 0
    Main.maybeGiver = None
    Main.maybeGivee = None
    Main.rosterOrQuit(filePath)
    Main.startNewYear()
    val givee = Main.maybeGivee.get
    val giver = Main.maybeGiver.get
    Main.giveeIsSuccess()
    assert(Players.getGivee(giver)(Main.agYear)(Main.aPlayers) == givee)
    assert(Players.getGiver(givee)(Main.agYear)(Main.aPlayers) == giver)
    assert(!Main.ageHat.contains(givee))
  }

}
