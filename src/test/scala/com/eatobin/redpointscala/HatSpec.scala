package com.eatobin.redpointscala

import com.eatobin.redpointscala.Hat._
import org.scalatest.flatspec.AnyFlatSpec

class HatSpec extends AnyFlatSpec {

  private val testHat: Set[String] = Set("RinSta", "JohLen", "GeoHar", "PauMcc")

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  private val players: Map[String, Player] =
    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)


  "A Hat" should "make itself given players" in {
    assert(hatMakeHat(players) == testHat)
  }

  it should "remove a puck" in {
    assert(hatRemovePuck("RinSta", testHat) == Set("JohLen", "GeoHar", "PauMcc"))
    assert(hatRemovePuck("RinStaX", Set()) == Set())
  }

  it should "discard a puck" in {
    assert(hatDiscardGivee("JohLen", Set("PauMcc")) == Set("PauMcc", "JohLen"))
  }

  it should "return discarded givees" in {
    assert(hatReturnDiscards(Set("GeoHar"), Set("PauMcc", "JohLen")) == Set("JohLen", "PauMcc", "GeoHar"))
  }
}
