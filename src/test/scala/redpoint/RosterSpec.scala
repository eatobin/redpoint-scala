package redpoint

import org.scalatest.FlatSpec

class RosterSpec extends FlatSpec {

  val bs = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen"
  val rl: List[List[String]] = List(List("The Beatles", "2014"), List("RinSta", "Ringo Starr", "JohLen", "GeoHar"), List("JohLen", "John Lennon", "PauMcc", "RinSta"), List("GeoHar", "George Harrison", "RinSta", "PauMcc"), List("PauMcc", "Paul McCartney", "GeoHar", "JohLen"))

  "A RosterList" should "create itself properly" in {
    assert(RosterUtility.makeRosterList(bs) == rl)
    //    assert(Borrower.getMaxBooks(br1) == 1)
  }

  //  it should "set a new name and maxBooks" in {
  //    assert(Borrower.setName("Borrower1", Borrower("Jack", 1)) == br1)
  //    assert(Borrower.setMaxBooks(1, Borrower("Borrower1", 11)) == br1)
  //  }
  //
  //  it should "return a string \"Borrower1 (1 books)\"" in {
  //    assert(Borrower.borrowerToString(br1) == "Borrower1 (1 books)")
  //  }

}
