// package redpoint

// import org.scalatest.flatspec.AnyFlatSpec

// class RosterSpec extends AnyFlatSpec {

//   private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar)))
//   private val johLen: Player = Player("John Lennon", Vector(GiftPair('PauMcc, 'RinSta)))
//   private val geoHar: Player = Player("George Harrison", Vector(GiftPair('RinSta, 'PauMcc)))
//   private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair('GeoHar, 'JohLen)))

//   private val players: Players =
//     Map('RinSta -> rinSta, 'JohLen -> johLen, 'GeoHar -> geoHar, 'PauMcc -> pauMcc)

//   private val roster: Roster = Roster("The Beatles", 2014, players)

//   "A Roster" should "return \"The Beatles\" rosterName" in {
//     assert(roster.rosterName == "The Beatles")
//   }

//   it should "return 2014 rosterYear" in {
//     assert(roster.rosterYear == 2014)
//   }

//   it should "return players" in {
//     assert(roster.players == players)
//   }
// }
