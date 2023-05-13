package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonStringTA
import com.eatobin.redpointscala.Hat._
import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.immutable.{SortedMap, SortedSet}

class HatSpec extends AnyFlatSpec {

  private val jsonString: JsonStringTA =
    "[\"RinSta\",\"JohLen\",\"GeoHar\",\"PauMcc\"]"
  private val testHat: SortedSet[String] =
    SortedSet("RinSta", "JohLen", "GeoHar", "PauMcc")

  private val rinSta: Player =
    Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player =
    Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player =
    Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player =
    Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))

  private val players: SortedMap[String, Player] =
    SortedMap(
      "RinSta" -> rinSta,
      "JohLen" -> johLen,
      "GeoHar" -> geoHar,
      "PauMcc" -> pauMcc
    )

  "A Hat" should "make itself given players" in {
    assert(hatMakeHat(players) == testHat)
  }

  it should "remove a puck" in {
    assert(
      hatRemovePuck("RinSta", testHat) == Set("JohLen", "GeoHar", "PauMcc")
    )
    assert(hatRemovePuck("RinStaX", SortedSet()) == Set())
  }

  it should "discard a puck" in {
    assert(
      hatDiscardGivee("JohLen", SortedSet("PauMcc")) == Set("PauMcc", "JohLen")
    )
  }

  it should "return discarded givees" in {
    assert(
      hatReturnDiscards(
        SortedSet("GeoHar"),
        SortedSet("PauMcc", "JohLen")
      ) == SortedSet("JohLen", "PauMcc", "GeoHar")
    )
  }

  it should "convert from JSON" in {
    val hatHat = hatJsonStringToHat(jsonString)
    assert(hatHat == Right(testHat))
  }
}
