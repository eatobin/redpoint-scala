case class GiftPair(givee: Symbol, giver: Symbol)

case class Player(pName: String, giftHist: List[GiftPair])

object RosterUtility {

  def makeRosterList(rosterString: String): List[List[String]] = {
    val rosterLines = rosterString.split("\n").toList
    rosterLines.map(l => l.split(", ").toList)
  }

  def makeRosterInfo(rosterList: List[List[String]]): List[String] =
    rosterList.head

  def makePlayersList(rosterList: List[List[String]]): List[List[String]] =
    rosterList.tail
}


// :load /Users/eatobin/scala_projects/redpoint-scala/src/main/scala/RosterUtility.scala
