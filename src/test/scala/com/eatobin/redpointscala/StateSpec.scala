package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonString
import com.eatobin.redpointscala.Hat.{Hat, hatDiscardGivee}
import com.eatobin.redpointscala.Players.{playersGetMyGivee, playersGetMyGiver}
import com.eatobin.redpointscala.State._
import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.immutable.{SortedMap, SortedSet}

class StateSpec extends AnyFlatSpec {

  private val testHat: Hat = SortedSet("RinSta")

  private val rinSta: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player = Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player = Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
  private val players: SortedMap[String, Player] =
    SortedMap("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoHar, "PauMcc" -> pauMcc)

  private val geoWhoops: Player = Player("George Harrison", Vector(GiftPair("GeoHar", "PauMcc")))
  private val pauYikes: Player = Player("Paul McCartney", Vector(GiftPair("GeoHar", "PauMcc")))
  private val playersWeird: SortedMap[String, Player] =
    SortedMap("RinSta" -> rinSta, "JohLen" -> johLen, "GeoHar" -> geoWhoops, "PauMcc" -> pauYikes)

  private val beatlesState: State = State(
    rosterName = "The Beatles",
    rosterYear = 2014,
    players = players,
    giftYear = 0,
    giveeHat = SortedSet(),
    giverHat = SortedSet(),
    maybeGivee = None,
    maybeGiver = None,
    discards = SortedSet(),
    quit = "n"
  )

  private val hawksState: State = State(
    "Blackhawks", 2010, SortedMap("TroBro" -> Player("Troy Brouwer", Vector(GiftPair("DavBol", "JoeQue"))), "PatKan" -> Player("Patrick Kane", Vector(GiftPair("BryBic", "CriHue"))), "JoeQue" -> Player("Joel Quenneville", Vector(GiftPair("TroBro", "AndLad"))), "NikHja" -> Player("Niklas Hjalmarsson", Vector(GiftPair("BreSea", "BriCam"))), "TomKop" -> Player("Tomas Kopecky", Vector(GiftPair("CriHue", "DunKei"))), "BryBic" -> Player("Bryan Bickell", Vector(GiftPair("MarHos", "PatKan"))), "AntNie" -> Player("Antti Niemi", Vector(GiftPair("JonToe", "MarHos"))), "PatSha" -> Player("Patrick Sharp", Vector(GiftPair("BriCam", "DavBol"))), "DunKei" -> Player("Duncan Keith", Vector(GiftPair("TomKop", "AdaBur"))), "BriCam" -> Player("Brian Campbell", Vector(GiftPair("NikHja", "PatSha"))), "BreSea" -> Player("Brent Seabrook", Vector(GiftPair("KriVer", "NikHja"))), "KriVer" -> Player("Kris Versteeg", Vector(GiftPair("AndLad", "BreSea"))), "MarHos" -> Player("Marian Hossa", Vector(GiftPair("AntNie", "BryBic"))), "AndLad" -> Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer"))), "DavBol" -> Player("Dave Bolland", Vector(GiftPair("PatSha", "TroBro"))), "CriHue" -> Player("Cristobal Huet", Vector(GiftPair("PatKan", "TomKop"))), "JonToe" -> Player("Jonathan Toews", Vector(GiftPair("AdaBur", "AntNie"))), "AdaBur" -> Player("Adam Burish", Vector(GiftPair("DunKei", "JonToe")))), 0, SortedSet(), SortedSet(), None, None, SortedSet(), "n"
  )

  private val beatlesJson: JsonString = """{"rosterName":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
  private val hawksJson: JsonString = """{"rosterName":"Blackhawks","rosterYear":2010,"players":{"TroBro":{"playerName":"Troy Brouwer","giftHistory":[{"givee":"DavBol","giver":"JoeQue"}]},"PatKan":{"playerName":"Patrick Kane","giftHistory":[{"givee":"BryBic","giver":"CriHue"}]},"JoeQue":{"playerName":"Joel Quenneville","giftHistory":[{"givee":"TroBro","giver":"AndLad"}]},"NikHja":{"playerName":"Niklas Hjalmarsson","giftHistory":[{"givee":"BreSea","giver":"BriCam"}]},"TomKop":{"playerName":"Tomas Kopecky","giftHistory":[{"givee":"CriHue","giver":"DunKei"}]},"BryBic":{"playerName":"Bryan Bickell","giftHistory":[{"givee":"MarHos","giver":"PatKan"}]},"AntNie":{"playerName":"Antti Niemi","giftHistory":[{"givee":"JonToe","giver":"MarHos"}]},"PatSha":{"playerName":"Patrick Sharp","giftHistory":[{"givee":"BriCam","giver":"DavBol"}]},"DunKei":{"playerName":"Duncan Keith","giftHistory":[{"givee":"TomKop","giver":"AdaBur"}]},"BriCam":{"playerName":"Brian Campbell","giftHistory":[{"givee":"NikHja","giver":"PatSha"}]},"BreSea":{"playerName":"Brent Seabrook","giftHistory":[{"givee":"KriVer","giver":"NikHja"}]},"KriVer":{"playerName":"Kris Versteeg","giftHistory":[{"givee":"AndLad","giver":"BreSea"}]},"MarHos":{"playerName":"Marian Hossa","giftHistory":[{"givee":"AntNie","giver":"BryBic"}]},"AndLad":{"playerName":"Andrew Ladd","giftHistory":[{"givee":"JoeQue","giver":"KriVer"}]},"DavBol":{"playerName":"Dave Bolland","giftHistory":[{"givee":"PatSha","giver":"TroBro"}]},"CriHue":{"playerName":"Cristobal Huet","giftHistory":[{"givee":"PatKan","giver":"TomKop"}]},"JonToe":{"playerName":"Jonathan Toews","giftHistory":[{"givee":"AdaBur","giver":"AntNie"}]},"AdaBur":{"playerName":"Adam Burish","giftHistory":[{"givee":"DunKei","giver":"JonToe"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""

  private val weirdState: State = State(
    rosterName = "The Beatles",
    rosterYear = 2014,
    players = playersWeird,
    giftYear = 0,
    giveeHat = SortedSet(),
    giverHat = SortedSet(),
    maybeGivee = None,
    maybeGiver = None,
    discards = SortedSet(),
    quit = "n"
  )

  private val rinStaPlus: Player = Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta")))

  "State" should "draw a puck" in {
    assert(stateDrawPuck(testHat).contains("RinSta"))
    assert(stateDrawPuck(SortedSet()).isEmpty)
  }

  it should "start a new year" in {
    val newState = stateStartNewYear(beatlesState)
    assert(newState.giftYear == 1)
    assert(newState.maybeGiver.isDefined)
    assert(newState.maybeGivee.isDefined)
    assert(rinStaPlus == newState.players("RinSta"))
    assert(newState.discards.isEmpty)
  }

  it should "select a new giver" in {
    val newState = stateStartNewYear(beatlesState)
    val newDiscards = hatDiscardGivee("GeoHar", newState.discards)
    assert(newDiscards.size == 1)
    val secondState = stateSelectNewGiver(newState)
    assert(secondState.giverHat.size == 3)
    assert(secondState.discards.isEmpty)
  }

  it should "have a successful givee" in {
    val newState = stateStartNewYear(beatlesState)
    val givee = newState.maybeGivee.get
    val giver = newState.maybeGiver.get
    val secondState = stateGiveeIsSuccess(newState)
    assert(playersGetMyGivee(giver)(secondState.players)(secondState.giftYear) == givee)
    assert(playersGetMyGiver(givee)(secondState.players)(secondState.giftYear) == giver)
    assert(!secondState.giveeHat.contains(givee))
  }

  it should "have a failing givee" in {
    val newState = stateStartNewYear(beatlesState)
    val givee = newState.maybeGivee.get
    val secondState = stateGiveeIsFailure(newState)
    assert(secondState.discards.contains(givee))
    assert(!secondState.giveeHat.contains(givee))
  }

  it should "report player errors" in {
    assert(stateErrors(weirdState) == Seq("GeoHar", "PauMcc"))
  }

  it should "print" in {
    statePrintResults(beatlesState)
    statePrintResults(weirdState)
  }

  it should "convert from JSON-Beatles" in {
    assert(stateJsonStringToState(beatlesJson) == Right(beatlesState))
  }

  it should "convert from JSON-Hawks" in {
    assert(stateJsonStringToState(hawksJson) == Right(hawksState))
  }
}
