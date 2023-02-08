package com.eatobin.redpointscala

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.immutable.SortedMap

class RulesSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(giver = "PauMcc", givee = "EriTob")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(giver = "GeoHar", givee = "SusSmi")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(giver = "JohLen", givee = "DonDuc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(giver = "RinSta", givee = "MicMou")))
  private val eriTob: Player = Player("Eric Tobin", Vector(GiftPair(giver = "MicMou", givee = "RinSta")))
  private val susSmi: Player = Player("Susan Smith", Vector(GiftPair(giver = "DonDuc", givee = "JohLen")))
  private val donDuc: Player = Player("Donald Duck", Vector(GiftPair(giver = "SusSmi", givee = "GeoHar")))
  private val micMou: Player = Player("Mickey Mouse", Vector(GiftPair(giver = "EriTob", givee = "PauMcc")))
  private val beatlesPlusPM: SortedMap[String, Player] =
    SortedMap("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc, "EriTob" -> eriTob, "SusSmi" -> susSmi, "DonDuc" -> donDuc, "MicMou" -> micMou)

  private var beatlesPlus6 = Players.playersAddYear(beatlesPlusPM)
  beatlesPlus6 = Players.playersUpdateMyGivee("RinSta")("GeoHar")(1)(beatlesPlus6)
  beatlesPlus6 = Players.playersAddYear(beatlesPlus6)
  beatlesPlus6 = Players.playersUpdateMyGivee("RinSta")("PauMcc")(2)(beatlesPlus6)
  beatlesPlus6 = Players.playersAddYear(beatlesPlus6)
  beatlesPlus6 = Players.playersUpdateMyGivee("RinSta")("EriTob")(3)(beatlesPlus6)
  beatlesPlus6 = Players.playersAddYear(beatlesPlus6)
  beatlesPlus6 = Players.playersUpdateMyGivee("RinSta")("SusSmi")(4)(beatlesPlus6)
  beatlesPlus6 = Players.playersAddYear(beatlesPlus6)
  beatlesPlus6 = Players.playersUpdateMyGivee("RinSta")("DonDuc")(5)(beatlesPlus6)
  beatlesPlus6 = Players.playersAddYear(beatlesPlus6)
  beatlesPlus6 = Players.playersUpdateMyGivee("RinSta")("MicMou")(6)(beatlesPlus6)

  "A Player" should "not give to itself" in {
    assertResult(true) {
      Rules.rulesGiveeNotSelf("RinSta", "GeoHar")
    }
    assertResult(false) {
      Rules.rulesGiveeNotSelf("RinSta", "RinSta")
    }
  }

  it should "not give to it's recip" in {
    assertResult(true) {
      Rules.rulesGiveeNotRecip("RinSta", "JohLen", 0, beatlesPlusPM)
    }
    assertResult(false) {
      Rules.rulesGiveeNotRecip("RinSta", "EriTob", 0, beatlesPlusPM)
    }
  }

  it should "not repeat for past four years" in {
    assertResult(true) {
      Rules.rulesGiveeNotRepeat("RinSta", "DonDuc", 2, beatlesPlus6)
    }
    assertResult(true) {
      Rules.rulesGiveeNotRepeat("RinSta", "PauMcc", 2, beatlesPlus6)
    }
    assertResult(false) {
      Rules.rulesGiveeNotRepeat("RinSta", "EriTob", 2, beatlesPlus6)
    }
    assertResult(false) {
      Rules.rulesGiveeNotRepeat("RinSta", "GeoHar", 2, beatlesPlus6)
    }
    assertResult(true) {
      Rules.rulesGiveeNotRepeat("RinSta", "MicMou", 6, beatlesPlus6)
    }
    assertResult(false) {
      Rules.rulesGiveeNotRepeat("RinSta", "MicMou", 7, beatlesPlus6)
    }
    assertResult(true) {
      Rules.rulesGiveeNotRepeat("RinSta", "MicMou", 6, beatlesPlus6)
    }
    assertResult(false) {
      Rules.rulesGiveeNotRepeat("RinSta", "PauMcc", 6, beatlesPlus6)
    }
    //    //    assert(!Rules.rulesGiveeNotRepeat("RinSta", "PauMcc", 5, beatlesPlus6))
    //    //    assert(!Rules.rulesGiveeNotRepeat("RinSta", "EriTob", 5, beatlesPlus6))
    //    //    assert(!Rules.rulesGiveeNotRepeat("RinSta", "SusSmi", 5, beatlesPlus6))
  }
}
