package com.eatobin.redpointscala

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.immutable.SortedMap

class RulesSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(giver = "SusSmi", givee = "JohLen")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(giver = "RinSta", givee = "GeoHar")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(giver = "JohLen", givee = "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(giver = "GeoHar", givee = "EriTob")))
  private val eriTob: Player = Player("Eric Tobin", Vector(GiftPair(giver = "PauMcc", givee = "SusSmi")))
  private val susSmi: Player = Player("Susan Smith", Vector(GiftPair(giver = "EriTob", givee = "RinSta")))
  private val beatlesPlusPM: SortedMap[String, Player] =
    SortedMap("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc, "EriTob" -> eriTob, "SusSmi" -> susSmi)

  private var beatlesPlus4 = Players.playersAddYear(beatlesPlusPM)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta")("GeoHar")(1)(beatlesPlus4)
  beatlesPlus4 = Players.playersAddYear(beatlesPlus4)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta")("PauMcc")(2)(beatlesPlus4)
  beatlesPlus4 = Players.playersAddYear(beatlesPlus4)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta")("EriTob")(3)(beatlesPlus4)
  beatlesPlus4 = Players.playersAddYear(beatlesPlus4)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta")("SusSmi")(4)(beatlesPlus4)

  "A Player" should "not give to itself" in {
    assert(Rules.rulesGiveeNotSelf("RinSta", "GeoHar"))
    assert(!Rules.rulesGiveeNotSelf("RinSta", "RinSta"))
  }

  it should "not give to it's recip" in {
    assert(Rules.rulesGiveeNotRecip("RinSta", "JohLen", 0, beatlesPlusPM))
    assert(!Rules.rulesGiveeNotRecip("RinSta", "SusSmi", 0, beatlesPlusPM))
  }

  it should "not repeat for four years" in {
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "JohLen", 2, beatlesPlus4))
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "GeoHar", 2, beatlesPlus4))
    assert(Rules.rulesGiveeNotRepeat("RinSta", "SusSmi", 2, beatlesPlus4))
    assert(Rules.rulesGiveeNotRepeat("RinSta", "JohLen", 5, beatlesPlus4))
//    assert(Rules.rulesGiveeNotRepeat("RinSta", "GeoHar", 5, beatlesPlus4))
//    assert(!Rules.rulesGiveeNotRepeat("RinSta", "PauMcc", 5, beatlesPlus4))
//    assert(!Rules.rulesGiveeNotRepeat("RinSta", "EriTob", 5, beatlesPlus4))
//    assert(!Rules.rulesGiveeNotRepeat("RinSta", "SusSmi", 5, beatlesPlus4))
  }
}
