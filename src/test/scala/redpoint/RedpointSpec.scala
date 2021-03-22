package redpoint

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
    //    val giver = Redpoint.maybeGiver.get
    //    Redpoint.giveeIsSuccess()
    //    assert(Players.getGivee(giver)(Redpoint.agYear)(Redpoint.aPlayers) == givee)
    //    assert(Players.getGiver(givee)(Redpoint.agYear)(Redpoint.aPlayers) == giver)
    //    assert(!Redpoint.ageHat.contains(givee))
  }

  //  (deftest givee-is-failure-test
  //  (reset! core/a-g-year 0)
  //  (reset! core/a-giver nil)
  //  (reset! core/a-givee nil)
  //  (core/roster-or-quit "resources-test/beatles.json")
  //  (core/start-new-year)
  //  (let [temp-ge (deref core/a-givee)]
  //  (core/givee-is-failure)
  //  (is (= temp-ge
  //  (some #{temp-ge} (deref core/a-discards))))
  //  (is (= nil
  //  (some #{temp-ge} (deref core/a-ge-hat))))))

}
