package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import com.eatobin.redpointscala.State.stateJsonStringToState
import io.circe.Error

object Main {

  private val hawksJson: JsonString = """{"rosterName":"Blackhawks","rosterYear":2010,"players":{"TroBro":{"playerName":"Troy Brouwer","giftHistory":[{"givee":"DavBol","giver":"JoeQue"}]},"PatKan":{"playerName":"Patrick Kane","giftHistory":[{"givee":"BryBic","giver":"CriHue"}]},"JoeQue":{"playerName":"Joel Quenneville","giftHistory":[{"givee":"TroBro","giver":"AndLad"}]},"NikHja":{"playerName":"Niklas Hjalmarsson","giftHistory":[{"givee":"BreSea","giver":"BriCam"}]},"TomKop":{"playerName":"Tomas Kopecky","giftHistory":[{"givee":"CriHue","giver":"DunKei"}]},"BryBic":{"playerName":"Bryan Bickell","giftHistory":[{"givee":"MarHos","giver":"PatKan"}]},"AntNie":{"playerName":"Antti Niemi","giftHistory":[{"givee":"JonToe","giver":"MarHos"}]},"PatSha":{"playerName":"Patrick Sharp","giftHistory":[{"givee":"BriCam","giver":"DavBol"}]},"DunKei":{"playerName":"Duncan Keith","giftHistory":[{"givee":"TomKop","giver":"AdaBur"}]},"BriCam":{"playerName":"Brian Campbell","giftHistory":[{"givee":"NikHja","giver":"PatSha"}]},"BreSea":{"playerName":"Brent Seabrook","giftHistory":[{"givee":"KriVer","giver":"NikHja"}]},"KriVer":{"playerName":"Kris Versteeg","giftHistory":[{"givee":"AndLad","giver":"BreSea"}]},"MarHos":{"playerName":"Marian Hossa","giftHistory":[{"givee":"AntNie","giver":"BryBic"}]},"AndLad":{"playerName":"Andrew Ladd","giftHistory":[{"givee":"JoeQue","giver":"KriVer"}]},"DavBol":{"playerName":"Dave Bolland","giftHistory":[{"givee":"PatSha","giver":"TroBro"}]},"CriHue":{"playerName":"Cristobal Huet","giftHistory":[{"givee":"PatKan","giver":"TomKop"}]},"JonToe":{"playerName":"Jonathan Toews","giftHistory":[{"givee":"AdaBur","giver":"AntNie"}]},"AdaBur":{"playerName":"Adam Burish","giftHistory":[{"givee":"DunKei","giver":"JonToe"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[]}"""



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
    val state: Either[Error, State] = stateJsonStringToState(hawksJson)
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
