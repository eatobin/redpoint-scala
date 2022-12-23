package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._


import com.eatobin.redpointscala.Rules.{rulesGiveeNotRecip, rulesGiveeNotRepeat, rulesGiveeNotSelf}

object Main {

  private val jsonString: String = "{\n  \"rosterName\": \"Blackhawks\",\n  \"rosterYear\": 2010,\n  \"players\": {\n    \"TroBro\": {\n      \"playerName\": \"Troy Brouwer\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"DavBol\",\n          \"giver\": \"JoeQue\"\n        }\n      ]\n    },\n    \"JoeQue\": {\n      \"playerName\": \"Joel Quenneville\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"TroBro\",\n          \"giver\": \"AndLad\"\n        }\n      ]\n    },\n    \"AdaBur\": {\n      \"playerName\": \"Adam Burish\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"DunKei\",\n          \"giver\": \"JonToe\"\n        }\n      ]\n    },\n    \"AndLad\": {\n      \"playerName\": \"Andrew Ladd\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"JoeQue\",\n          \"giver\": \"KriVer\"\n        }\n      ]\n    },\n    \"AntNie\": {\n      \"playerName\": \"Antti Niemi\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"JonToe\",\n          \"giver\": \"MarHos\"\n        }\n      ]\n    },\n    \"BreSea\": {\n      \"playerName\": \"Brent Seabrook\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"KriVer\",\n          \"giver\": \"NikHja\"\n        }\n      ]\n    },\n    \"BryBic\": {\n      \"playerName\": \"Bryan Bickell\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"MarHos\",\n          \"giver\": \"PatKan\"\n        }\n      ]\n    },\n    \"CriHue\": {\n      \"playerName\": \"Cristobal Huet\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"PatKan\",\n          \"giver\": \"TomKop\"\n        }\n      ]\n    },\n    \"DavBol\": {\n      \"playerName\": \"Dave Bolland\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"PatSha\",\n          \"giver\": \"TroBro\"\n        }\n      ]\n    },\n    \"DunKei\": {\n      \"playerName\": \"Duncan Keith\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"TomKop\",\n          \"giver\": \"AdaBur\"\n        }\n      ]\n    },\n    \"JonToe\": {\n      \"playerName\": \"Jonathan Toews\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"AdaBur\",\n          \"giver\": \"AntNie\"\n        }\n      ]\n    },\n    \"KriVer\": {\n      \"playerName\": \"Kris Versteeg\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"AndLad\",\n          \"giver\": \"BreSea\"\n        }\n      ]\n    },\n    \"MarHos\": {\n      \"playerName\": \"Marian Hossa\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"AntNie\",\n          \"giver\": \"BryBic\"\n        }\n      ]\n    },\n    \"NikHja\": {\n      \"playerName\": \"Niklas Hjalmarsson\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"BreSea\",\n          \"giver\": \"BriCam\"\n        }\n      ]\n    },\n    \"PatKan\": {\n      \"playerName\": \"Patrick Kane\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"BryBic\",\n          \"giver\": \"CriHue\"\n        }\n      ]\n    },\n    \"PatSha\": {\n      \"playerName\": \"Patrick Sharp\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"BriCam\",\n          \"giver\": \"DavBol\"\n        }\n      ]\n    },\n    \"TomKop\": {\n      \"playerName\": \"Tomas Kopecky\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"CriHue\",\n          \"giver\": \"DunKei\"\n        }\n      ]\n    },\n    \"BriCam\": {\n      \"playerName\": \"Brian Campbell\",\n      \"giftHistory\": [\n        {\n          \"givee\": \"NikHja\",\n          \"giver\": \"PatSha\"\n        }\n      ]\n    }\n  }\n}"

  def mainJsonStringToState(jsonString: String): Either[Error, State] =
    decode[State](jsonString)


  //  private val troBro: Player = Player("Troy Brouwer", Vector(GiftPair("DavBol", "JoeQue")))
  //  private val joeQue: Player = Player("Joel Quenneville", Vector(GiftPair("TroBro", "AndLad")))
  //  private val adaBur: Player = Player("Adam Burish", Vector(GiftPair("DunKei", "JonToe")))
  //  private val andLad: Player = Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer")))
  //
  //  private val antNie: Player = Player("Antti Niemi", Vector(GiftPair("JonToe", "MarHos")))
  //  private val breSea: Player = Player("Brent Seabrook", Vector(GiftPair("KriVer", "NikHja")))
  //  private val bryBic: Player = Player("Bryan Bickell", Vector(GiftPair("MarHos", "PatKan")))
  //  private val andLad: Player = Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer")))
  //  private val andLad: Player = Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer")))
  //  private val andLad: Player = Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer")))
  //  private val andLad: Player = Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer")))
  //
  //  // TODO keep adding players from blackhawks.json!!
  //  private val players: Map[String, Player] =
  //    Map("TroBro" -> troBro, "JoeQue" -> joeQue, "AdaBur" -> adaBur, "AndLad" -> andLad)

  def main(args: Array[String]): Unit = {
    val state: Either[Error, State] = mainJsonStringToState(jsonString)
    print(state)

    //    while (helpersPrintAndAsk(aRosterName)(aRosterYear).toLowerCase != "q") {
    //      helpersStartNewYear()
    //      while (aMaybeGiver.isDefined) {
    //        while (aMaybeGivee.isDefined) {
    //          if (rulesGiveeNotSelf(aMaybeGiver.get, aMaybeGivee.get) &&
    //            rulesGiveeNotRecip(aMaybeGiver.get, aMaybeGivee.get, aGiftYear, aPlayers) &&
    //            rulesGiveeNotRepeat(aMaybeGiver.get, aMaybeGivee.get, aGiftYear, aPlayers)) {
    //            helpersGiveeIsSuccess()
    //          } else {
    //            helpersGiveeIsFailure()
    //          }
    //        }
    //        helpersSelectNewGiver()
    //      }
    //    }
    //    println()
    //    println("This was fun!")
    //    println("Talk about a position with Redpoint?")
    //    println("Please call: Eric Tobin 773-679-6617")
    //    println()
    //  }
  }
}
