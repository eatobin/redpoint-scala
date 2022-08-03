package com.eatobin.redpointscala

import org.scalatest.flatspec.AnyFlatSpec

class RulesSpec extends AnyFlatSpec {

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair(giver = "KarLav", givee = "JohLen")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair(giver = "RinSta", givee = "GeoHar")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair(giver = "JohLen", givee = "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair(giver = "GeoHar", givee = "EriTob")))
  private val eriTob: Player = Player("Eric Tobin", Vector(GiftPair(giver = "PauMcc", givee = "KarLav")))
  private val karLav: Player = Player("Karen Lavengood", Vector(GiftPair(giver = "EriTob", givee = "RinSta")))
  private val beatlesPlusPM: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc, "EriTob" -> eriTob, "KarLav" -> karLav)

  private var beatlesPlus4 = Players.addYear(beatlesPlusPM)
  beatlesPlus4 = Players.updateGivee("RinSta", 1, "GeoHar", beatlesPlus4)
  beatlesPlus4 = Players.addYear(beatlesPlus4)
  beatlesPlus4 = Players.updateGivee("RinSta", 2, "PauMcc", beatlesPlus4)
  beatlesPlus4 = Players.addYear(beatlesPlus4)
  beatlesPlus4 = Players.updateGivee("RinSta", 3, "EriTob", beatlesPlus4)
  beatlesPlus4 = Players.addYear(beatlesPlus4)
  beatlesPlus4 = Players.updateGivee("RinSta", 4, "KarLav", beatlesPlus4)

  "A Player" should "not give to itself" in {
    assert(Rules.giveeNotSelf("RinSta", "GeoHar"))
    assert(!Rules.giveeNotSelf("RinSta", "RinSta"))
  }

  it should "not give to it's recip" in {
    assert(Rules.giveeNotRecip("RinSta", "JohLen", 0, beatlesPlusPM))
    assert(!Rules.giveeNotRecip("RinSta", "KarLav", 0, beatlesPlusPM))
  }

  it should "not repeat for three years" in {
    assert(!Rules.giveeNotRepeat("RinSta", "JohLen", 2, beatlesPlus4))
    assert(!Rules.giveeNotRepeat("RinSta", "GeoHar", 2, beatlesPlus4))
    assert(Rules.giveeNotRepeat("RinSta", "KarLav", 2, beatlesPlus4))
    assert(Rules.giveeNotRepeat("RinSta", "JohLen", 5, beatlesPlus4))
    assert(Rules.giveeNotRepeat("RinSta", "GeoHar", 5, beatlesPlus4))
    assert(!Rules.giveeNotRepeat("RinSta", "PauMcc", 5, beatlesPlus4))
    assert(!Rules.giveeNotRepeat("RinSta", "EriTob", 5, beatlesPlus4))
    assert(!Rules.giveeNotRepeat("RinSta", "KarLav", 5, beatlesPlus4))
  }
}
