import redpoint.RosterStringCheck._

import scala.collection.immutable.HashMap

type RawString = String
type Scrubbed = String
type ErrorString = String

type RName = String
type RYear = Int
type PlayersList = List[List[String]]
type PlrSym = Symbol
type Givee = PlrSym
type Giver = PlrSym
type GiftPair = HashMap[PlrSym, PlrSym]

//  type PlayerLine = List[String]
//
//  type PlrSym = Symbol
//  type Givee = PlrSym
//  type Giver = PlrSym
//
//  type PName = String
//  type GiftHist = Vector[GiftPair]
//  type GYear = Int

//
//  type PlayerKV = (PlrSym, Player)
//  type PlayersKVList = List[(PlrSym, Player)]
//  type PlayersMap = Map[PlrSym, Player]

// Given a scrubbed return the roster name
def getRosterName(scrubbed: Scrubbed): RName = {
  lines(scrubbed).head.split(",").head
}

// Given a scrubbed return the roster year
def getRosterYear(scrubbed: Scrubbed): RYear = {
  lines(scrubbed).head.split(",").last.toInt
}

def makePlayersList(scrubbed: Scrubbed): PlayersList = {
  scrubbed
    .split("\n")
    .toList
    .tail
    .map(l => l.split(",")
      .toList)
}

def makeGiftPair(givee: String, giver: String): GiftPair =
  HashMap(('givee, Symbol(givee)), ('giver, Symbol(giver)))

val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"

getRosterName(ss)
getRosterYear(ss)
makePlayersList(ss)
makeGiftPair("me", "you")
