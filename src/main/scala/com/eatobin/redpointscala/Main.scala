package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import com.eatobin.redpointscala.State._
import io.circe.Error

object Main {
  private val hawksJson: JsonString = """{"rosterName":"Blackhawks","rosterYear":2010,"players":{"TroBro":{"playerName":"Troy Brouwer","giftHistory":[{"givee":"DavBol","giver":"JoeQue"}]},"PatKan":{"playerName":"Patrick Kane","giftHistory":[{"givee":"BryBic","giver":"CriHue"}]},"JoeQue":{"playerName":"Joel Quenneville","giftHistory":[{"givee":"TroBro","giver":"AndLad"}]},"NikHja":{"playerName":"Niklas Hjalmarsson","giftHistory":[{"givee":"BreSea","giver":"BriCam"}]},"TomKop":{"playerName":"Tomas Kopecky","giftHistory":[{"givee":"CriHue","giver":"DunKei"}]},"BryBic":{"playerName":"Bryan Bickell","giftHistory":[{"givee":"MarHos","giver":"PatKan"}]},"AntNie":{"playerName":"Antti Niemi","giftHistory":[{"givee":"JonToe","giver":"MarHos"}]},"PatSha":{"playerName":"Patrick Sharp","giftHistory":[{"givee":"BriCam","giver":"DavBol"}]},"DunKei":{"playerName":"Duncan Keith","giftHistory":[{"givee":"TomKop","giver":"AdaBur"}]},"BriCam":{"playerName":"Brian Campbell","giftHistory":[{"givee":"NikHja","giver":"PatSha"}]},"BreSea":{"playerName":"Brent Seabrook","giftHistory":[{"givee":"KriVer","giver":"NikHja"}]},"KriVer":{"playerName":"Kris Versteeg","giftHistory":[{"givee":"AndLad","giver":"BreSea"}]},"MarHos":{"playerName":"Marian Hossa","giftHistory":[{"givee":"AntNie","giver":"BryBic"}]},"AndLad":{"playerName":"Andrew Ladd","giftHistory":[{"givee":"JoeQue","giver":"KriVer"}]},"DavBol":{"playerName":"Dave Bolland","giftHistory":[{"givee":"PatSha","giver":"TroBro"}]},"CriHue":{"playerName":"Cristobal Huet","giftHistory":[{"givee":"PatKan","giver":"TomKop"}]},"JonToe":{"playerName":"Jonathan Toews","giftHistory":[{"givee":"AdaBur","giver":"AntNie"}]},"AdaBur":{"playerName":"Adam Burish","giftHistory":[{"givee":"DunKei","giver":"JonToe"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""

  def main(args: Array[String]): Unit = {
    val rightState: Either[Error, State] = stateJsonStringToState(hawksJson)
    rightState match {
      case Left(e) => println(s"Error is: $e")
      case Right(state) =>
        statePrintResults(state)
        if (state.quit.toLowerCase == "q") {
          println()
          println("This was fun!")
          println("Talk about a position with Redpoint?")
          println("Please call: Eric Tobin 773-679-6617")
          println()
        } else {
          stateAskContinue(statePrintResults(stateUpdateAndRunNewYear(state)))
        }



      //        var varState: State = valState
      //        while (stateAskContinue(valState).quit.toLowerCase != "q") {
      //          stateGiveeIsSuccessOrFailure(valState)
      //          varState = stateStartNewYear(varState)
      //          while (varState.maybeGiver.isDefined) {
      //            while (varState.maybeGivee.isDefined) {
      //              varState = stateGiveeIsSuccessOrFailure(varState)
      //            }
      //            varState = stateSelectNewGiver(varState)
      //          }
    }
    //        println()
    //        println("This was fun!")
    //        println("Talk about a position with Redpoint?")
    //        println("Please call: Eric Tobin 773-679-6617")
    //        println()
  }
}

