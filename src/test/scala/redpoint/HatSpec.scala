//package redpoint
//
//import org.scalatest.flatspec.AnyFlatSpec
//import redpoint.Hats._
//
//class HatSpec extends AnyFlatSpec {
//
//  private val testHat: Set[String] = Set("RinSta", "JohLen", "GeoHar", "PauMcc")
//
//  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
//  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
//  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
//  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
//
//  private val players: Map[String, Player] =
//    Map("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)
//
//
//  "A Hat" should "make itself given players" in {
//    assert(makeHat(players) == testHat)
//  }
//
//  it should "remove a puck" in {
//    assert(removePuck(testHat, "RinSta") == Set("JohLen", "GeoHar", "PauMcc"))
//    assert(removePuck(Set(), "RinStaX") == Set())
//  }
//
//  it should "discard a puck" in {
//    assert(discardGivee(Set("PauMcc"), "JohLen") == Set("PauMcc", "JohLen"))
//  }
//
//  it should "return discarded givees" in {
//    assert(returnDiscards(Set("PauMcc", "JohLen"), Set("GeoHar")) == Set("JohLen", "PauMcc", "GeoHar"))
//  }
//}
