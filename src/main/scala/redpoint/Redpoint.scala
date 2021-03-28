package redpoint

import scala.io.Source

object Redpoint {
  var agYear: Int = 0
  var maybeGiver: Option[String] = None
  var maybeGivee: Option[String] = None
  var aPlayers: Map[String, Player] = Map()
  var agrHat: Set[String] = Set()
  var ageHat: Set[String] = Set()
  var aDiscards: Set[String] = Set()
  var aRosterName: String = ""
  var aRosterYear: Int = 0
  var filePath: String = "resources/blackhawks.json"

  def main(args: Array[String]): Unit = {
    println(agYear, maybeGiver, maybeGivee, aPlayers, agrHat, ageHat, aDiscards, aRosterName, aRosterYear, filePath)
  }

  def readFileIntoJsonString(fp: String): Either[ErrorString, JsonString] =
    try {
      val bufferedSource = Source.fromFile(fp)
      val js = bufferedSource.getLines().mkString
      bufferedSource.close
      Right(js)
    } catch {
      case e: Exception =>
        Left(e.getMessage)
    }

  def rosterOrQuit(fp: String): Unit = {
    val rosterStringEither = readFileIntoJsonString(fp)
    rosterStringEither match {
      case Right(rs) =>
        val rosterEither: Either[ErrorString, Roster] = Roster.jsonStringToRoster(Right(rs))
        rosterEither match {
          case Right(r) =>
            aRosterName = r.rosterName
            aRosterYear = r.rosterYear
            aPlayers = r.players
          case Left(pe) =>
            println(pe)
        }
      case Left(fe) =>
        println(fe)
    }
  }

  private def random(s: Set[String]): String = {
    val n = util.Random.nextInt(s.size)
    s.iterator.drop(n).next()
  }

  def drawPuck(hat: Set[String]): Option[String] = {
    if (hat.nonEmpty) {
      Some(random(hat))
    } else {
      None
    }
  }

  def startNewYear(): Unit = {
    agYear = agYear + 1
    aPlayers = Players.addYear(aPlayers)
    agrHat = Hats.makeHat(aPlayers)
    ageHat = Hats.makeHat(aPlayers)
    maybeGiver = drawPuck(agrHat)
    maybeGivee = drawPuck(ageHat)
    aDiscards = Set()
  }

  def selectNewGiver(): Unit = {
    val giver: String = maybeGiver.get
    agrHat = Hats.removePuck(giver)(agrHat)
    ageHat = Hats.returnDiscards(aDiscards)(ageHat)
    aDiscards = Set()
    maybeGiver = drawPuck(agrHat)
    maybeGivee = drawPuck(ageHat)
  }

  def giveeIsSuccess(): Unit = {
    val giver: String = maybeGiver.get
    val givee: String = maybeGivee.get
    aPlayers = Players.updateGivee(giver)(agYear)(givee)(aPlayers)
    aPlayers = Players.updateGiver(givee)(agYear)(giver)(aPlayers)
    ageHat = Hats.removePuck(givee)(ageHat)
    maybeGivee = None
  }

  def giveeIsFailure(): Unit = {
    val givee: String = maybeGivee.get
    ageHat = Hats.removePuck(givee)(ageHat)
    aDiscards = Hats.discardGivee(givee)(aDiscards)
    maybeGivee = drawPuck(ageHat)
  }
  //(defn errors? []
  //  (seq (for [plr-sym (keys (into (sorted-map) (deref a-players)))
  //             :let [giver-code (plrs/players-get-giver (deref a-players) plr-sym (deref a-g-year))
  //                   givee-code (plrs/players-get-givee (deref a-players) plr-sym (deref a-g-year))]
  //             :when (or (= plr-sym giver-code) (= plr-sym givee-code))]
  //         plr-sym)))
  def errors(): Unit = {
    val MoreThanTwenty = for (language <- LanguageBase
                              if (language.article >= 20 && language.article < 30)) // Filters

    // i.e. add this to a list
      yield language.name
  }
}


//// Creating case class
//        case class Language(name: String, article: Int)
//
//        val LanguageBase = List(Language("Scala", 26),
//                                Language("Csharp", 32),
//                                Language("Perl", 42),
//                                Language("Java", 22))
//
//        // Applying for comprehensions
//        // Generator
//        // Definition
//        val MoreThanTwenty = for (language <- LanguageBase
//        if (language.article >=20 && language.article < 30))// Filters
//
//         // i.e. add this to a list
//          yield language.name
//
//        // Print more than twenty
//        MoreThanTwenty.foreach(name => println(name))
//
//
//        Output :
//
//Scala
//Java

//
