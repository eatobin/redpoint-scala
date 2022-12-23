package com.eatobin.redpointscala

import io.circe.Error
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import com.eatobin.redpointscala.Rules.{rulesGiveeNotRecip, rulesGiveeNotRepeat, rulesGiveeNotSelf}

object Main {

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
    val state: Either[Error, State] = mainJsonStringToState("nope.json")

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
