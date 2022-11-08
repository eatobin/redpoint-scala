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

  private var beatlesPlus4 = Players.playersAddYear(beatlesPlusPM)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta", 1, "GeoHar", beatlesPlus4)
  beatlesPlus4 = Players.playersAddYear(beatlesPlus4)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta", 2, "PauMcc", beatlesPlus4)
  beatlesPlus4 = Players.playersAddYear(beatlesPlus4)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta", 3, "EriTob", beatlesPlus4)
  beatlesPlus4 = Players.playersAddYear(beatlesPlus4)
  beatlesPlus4 = Players.playersUpdateMyGivee("RinSta", 4, "KarLav", beatlesPlus4)

  "A Player" should "not give to itself" in {
    assert(Rules.rulesGiveeNotSelf("RinSta", "GeoHar"))
    assert(!Rules.rulesGiveeNotSelf("RinSta", "RinSta"))
  }

  it should "not give to it's recip" in {
    assert(Rules.rulesGiveeNotRecip("RinSta", "JohLen", 0, beatlesPlusPM))
    assert(!Rules.rulesGiveeNotRecip("RinSta", "KarLav", 0, beatlesPlusPM))
  }

  it should "not repeat for three years" in {
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "JohLen", 2, beatlesPlus4))
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "GeoHar", 2, beatlesPlus4))
    assert(Rules.rulesGiveeNotRepeat("RinSta", "KarLav", 2, beatlesPlus4))
    assert(Rules.rulesGiveeNotRepeat("RinSta", "JohLen", 5, beatlesPlus4))
    assert(Rules.rulesGiveeNotRepeat("RinSta", "GeoHar", 5, beatlesPlus4))
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "PauMcc", 5, beatlesPlus4))
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "EriTob", 5, beatlesPlus4))
    assert(!Rules.rulesGiveeNotRepeat("RinSta", "KarLav", 5, beatlesPlus4))
  }
}
