package com.eatobin.redpointscala

import com.eatobin.redpointscala.GiftPair.JsonStringTA
import com.eatobin.redpointscala.Hat.HatTA
import com.eatobin.redpointscala.MyState._
import com.eatobin.redpointscala.Players.{playersGetMyGivee, playersGetMyGiver}
import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.immutable.{SortedMap, SortedSet}

class MyStateSpec extends AnyFlatSpec {

  private val testHat: HatTA = SortedSet("RinSta")

  private val rinSta: Player =
    Player("Ringo Starr", Vector(GiftPair("JohLen", "GeoHar")))
  private val johLen: Player =
    Player("John Lennon", Vector(GiftPair("PauMcc", "RinSta")))
  private val geoHar: Player =
    Player("George Harrison", Vector(GiftPair("RinSta", "PauMcc")))
  private val pauMcc: Player =
    Player("Paul McCartney", Vector(GiftPair("GeoHar", "JohLen")))
  private val players: SortedMap[String, Player] =
    SortedMap(
      "RinSta" -> rinSta,
      "JohLen" -> johLen,
      "GeoHar" -> geoHar,
      "PauMcc" -> pauMcc
    )

  private val geoWhoops: Player =
    Player("geoWhoops", Vector(GiftPair("GeoHar", "PauMcc")))
  private val pauYikes: Player =
    Player("pauYikes", Vector(GiftPair("GeoHar", "PauMcc")))
  private val playersWeird: SortedMap[String, Player] =
    SortedMap(
      "RinSta" -> rinSta,
      "JohLen" -> johLen,
      "GeoHar" -> geoWhoops,
      "PauMcc" -> pauYikes
    )

  private val beatlesState0: MyState = MyState(
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

  private val hawksState: MyState = MyState(
    "Blackhawks",
    2010,
    SortedMap(
      "TroBro" -> Player("Troy Brouwer", Vector(GiftPair("DavBol", "JoeQue"))),
      "PatKan" -> Player("Patrick Kane", Vector(GiftPair("BryBic", "CriHue"))),
      "JoeQue" -> Player(
        "Joel Quenneville",
        Vector(GiftPair("TroBro", "AndLad"))
      ),
      "NikHja" -> Player(
        "Niklas Hjalmarsson",
        Vector(GiftPair("BreSea", "BriCam"))
      ),
      "TomKop" -> Player("Tomas Kopecky", Vector(GiftPair("CriHue", "DunKei"))),
      "BryBic" -> Player("Bryan Bickell", Vector(GiftPair("MarHos", "PatKan"))),
      "AntNie" -> Player("Antti Niemi", Vector(GiftPair("JonToe", "MarHos"))),
      "PatSha" -> Player("Patrick Sharp", Vector(GiftPair("BriCam", "DavBol"))),
      "DunKei" -> Player("Duncan Keith", Vector(GiftPair("TomKop", "AdaBur"))),
      "BriCam" -> Player(
        "Brian Campbell",
        Vector(GiftPair("NikHja", "PatSha"))
      ),
      "BreSea" -> Player(
        "Brent Seabrook",
        Vector(GiftPair("KriVer", "NikHja"))
      ),
      "KriVer" -> Player("Kris Versteeg", Vector(GiftPair("AndLad", "BreSea"))),
      "MarHos" -> Player("Marian Hossa", Vector(GiftPair("AntNie", "BryBic"))),
      "AndLad" -> Player("Andrew Ladd", Vector(GiftPair("JoeQue", "KriVer"))),
      "DavBol" -> Player("Dave Bolland", Vector(GiftPair("PatSha", "TroBro"))),
      "CriHue" -> Player(
        "Cristobal Huet",
        Vector(GiftPair("PatKan", "TomKop"))
      ),
      "JonToe" -> Player(
        "Jonathan Toews",
        Vector(GiftPair("AdaBur", "AntNie"))
      ),
      "AdaBur" -> Player("Adam Burish", Vector(GiftPair("DunKei", "JonToe")))
    ),
    0,
    SortedSet(),
    SortedSet(),
    None,
    None,
    SortedSet(),
    "n"
  )

  private val beatlesJson: JsonStringTA =
    """{"rosterName":"The Beatles","rosterYear":2014,"players":{"RinSta":{"playerName":"Ringo Starr","giftHistory":[{"givee":"JohLen","giver":"GeoHar"}]},"JohLen":{"playerName":"John Lennon","giftHistory":[{"givee":"PauMcc","giver":"RinSta"}]},"GeoHar":{"playerName":"George Harrison","giftHistory":[{"givee":"RinSta","giver":"PauMcc"}]},"PauMcc":{"playerName":"Paul McCartney","giftHistory":[{"givee":"GeoHar","giver":"JohLen"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""
  private val hawksJson: JsonStringTA =
    """{"rosterName":"Blackhawks","rosterYear":2010,"players":{"TroBro":{"playerName":"Troy Brouwer","giftHistory":[{"givee":"DavBol","giver":"JoeQue"}]},"PatKan":{"playerName":"Patrick Kane","giftHistory":[{"givee":"BryBic","giver":"CriHue"}]},"JoeQue":{"playerName":"Joel Quenneville","giftHistory":[{"givee":"TroBro","giver":"AndLad"}]},"NikHja":{"playerName":"Niklas Hjalmarsson","giftHistory":[{"givee":"BreSea","giver":"BriCam"}]},"TomKop":{"playerName":"Tomas Kopecky","giftHistory":[{"givee":"CriHue","giver":"DunKei"}]},"BryBic":{"playerName":"Bryan Bickell","giftHistory":[{"givee":"MarHos","giver":"PatKan"}]},"AntNie":{"playerName":"Antti Niemi","giftHistory":[{"givee":"JonToe","giver":"MarHos"}]},"PatSha":{"playerName":"Patrick Sharp","giftHistory":[{"givee":"BriCam","giver":"DavBol"}]},"DunKei":{"playerName":"Duncan Keith","giftHistory":[{"givee":"TomKop","giver":"AdaBur"}]},"BriCam":{"playerName":"Brian Campbell","giftHistory":[{"givee":"NikHja","giver":"PatSha"}]},"BreSea":{"playerName":"Brent Seabrook","giftHistory":[{"givee":"KriVer","giver":"NikHja"}]},"KriVer":{"playerName":"Kris Versteeg","giftHistory":[{"givee":"AndLad","giver":"BreSea"}]},"MarHos":{"playerName":"Marian Hossa","giftHistory":[{"givee":"AntNie","giver":"BryBic"}]},"AndLad":{"playerName":"Andrew Ladd","giftHistory":[{"givee":"JoeQue","giver":"KriVer"}]},"DavBol":{"playerName":"Dave Bolland","giftHistory":[{"givee":"PatSha","giver":"TroBro"}]},"CriHue":{"playerName":"Cristobal Huet","giftHistory":[{"givee":"PatKan","giver":"TomKop"}]},"JonToe":{"playerName":"Jonathan Toews","giftHistory":[{"givee":"AdaBur","giver":"AntNie"}]},"AdaBur":{"playerName":"Adam Burish","giftHistory":[{"givee":"DunKei","giver":"JonToe"}]}},"giftYear":0,"giveeHat":[],"giverHat":[],"maybeGivee":null,"maybeGiver":null,"discards":[],"quit":"n"}"""

  private val freshHat: SortedSet[String] =
    SortedSet("RinSta", "JohLen", "GeoHar", "PauMcc")

  private val weirdState: MyState = MyState(
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

  private val rinStaPlus: Player = Player(
    "Ringo Starr",
    Vector(GiftPair("JohLen", "GeoHar"), GiftPair("RinSta", "RinSta"))
  )

  "MyState" should "draw a puck" in {
    assert(myStateDrawPuck(testHat).contains("RinSta"))
    assert(myStateDrawPuck(SortedSet()).isEmpty)
  }

  it should "start a new year" in {
    val beatlesState1 = myStateStartNewYear(beatlesState0)
    assert(beatlesState1.giftYear == 1)
    assert(beatlesState1.giveeHat == freshHat)
    assert(beatlesState1.giverHat == freshHat)
    assert(beatlesState1.maybeGiver.isDefined)
    assert(beatlesState1.maybeGivee.isDefined)
    assert(rinStaPlus == beatlesState1.players("RinSta"))
    assert(beatlesState1.discards.isEmpty)
  }

  it should "have a failing givee" in {
    val beatlesState1 = myStateStartNewYear(beatlesState0)
    val badGivee = beatlesState1.maybeGivee.get
    val beatlesState2 = myStateGiveeIsFailure(beatlesState1)
    assert(!beatlesState2.giveeHat.contains(badGivee))
    assert(beatlesState2.maybeGivee.get != badGivee)
    assert(beatlesState2.discards.contains(badGivee))
  }

  it should "have a successful givee" in {
    val beatlesState1 = myStateStartNewYear(beatlesState0)
    val goodGivee = beatlesState1.maybeGivee.get
    val goodGiver = beatlesState1.maybeGiver.get
    val beatlesState2 = myStateGiveeIsSuccess(beatlesState1)
    assert(
      playersGetMyGivee(goodGiver)(beatlesState2.players)(
        beatlesState2.giftYear
      ) == goodGivee
    )
    assert(
      playersGetMyGiver(goodGivee)(beatlesState2.players)(
        beatlesState2.giftYear
      ) == goodGiver
    )
    assert(!beatlesState2.giveeHat.contains(goodGivee))
    assert(beatlesState2.maybeGivee.isEmpty)
  }

  it should "select a new giver" in {
    val beatlesState1 = myStateStartNewYear(beatlesState0)
    val badGivee = beatlesState1.maybeGivee.get
    val beatlesState2 = myStateGiveeIsFailure(beatlesState1)
    val goodGivee = beatlesState2.maybeGivee.get
    val goodGiver = beatlesState2.maybeGiver.get
    val beatlesState3 = myStateGiveeIsSuccess(beatlesState2)
    val beatlesState4 = myStateSelectNewGiver(beatlesState3)
    assert(beatlesState4.giveeHat.contains(badGivee))
    assert(!beatlesState4.giveeHat.contains(goodGivee))
    assert(!beatlesState4.giverHat.contains(goodGiver))
    assert(beatlesState4.maybeGivee.get != goodGivee)
    assert(beatlesState4.maybeGiver.get != goodGiver)
    assert(beatlesState4.discards.isEmpty)
  }

  it should "report player errors" in {
    assert(myStateErrors(weirdState) == Seq("GeoHar", "PauMcc"))
  }

  it should "print itself" in {
    assert(myStatePrintResults(beatlesState0) == beatlesState0)
    assert(myStatePrintResults(weirdState) == weirdState)
  }

  it should "convert from JSON-Beatles" in {
    assert(myStateJsonStringToMyState(beatlesJson) == Right(beatlesState0))
  }

  it should "convert from JSON-Hawks" in {
    assert(myStateJsonStringToMyState(hawksJson) == Right(hawksState))
  }
}
