package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.{a, convertToAnyShouldWrapper}
import redpoint.Main._

class MainSpec extends AnyFlatSpec {

  private val filePath: String = "src/test/resources/beatles.json"
  private val badFilePath: String = "nope.json"
  private val badJsonFile: String = "src/test/resources/bad-json.json"

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(Symbol("PauMcc"), Symbol("RinSta"))))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(Symbol("RinSta"), Symbol("PauMcc"))))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(Symbol("GeoHar"), Symbol("JohLen"))))
  private val players: Map[Symbol, Player] =
    Map(Symbol("RinSta") -> rinSta, Symbol("JohLen") -> johLen, Symbol("GeoHar") -> geoHar, Symbol("PauMcc") -> pauMcc)

  private val rinStaPlus: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("RinSta"), Symbol("RinSta"))))
  private val testHat: Set[Symbol] = Set(Symbol("RinSta"))

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
    assert(drawPuck(testHat).contains(Symbol("RinSta")))
    assert(drawPuck(Set()).isEmpty)
  }

  it should "start a new year" in {
    agYear = 0
    aGiver = None
    aGivee = None
    rosterOrQuit(filePath)
    startNewYear()
    assert(agYear == 1)
    assert(aGiver.isDefined)
    assert(aGivee.isDefined)
    assert(rinStaPlus == aPlayers(Symbol("RinSta")))
    assert(aDiscards.isEmpty)
  }
}
