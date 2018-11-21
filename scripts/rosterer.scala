import redpoint.RosterStringCheck._

type Scrubbed = String
type RName = String

// Given a scrubbed return the roster name
def getRosterName(scrubbed: Scrubbed): RName = {
  lines(scrubbed).head.split(",").head
}

type RYear = Int

// Given a scrubbed return the roster year
def getRosterYear(scrubbed: Scrubbed): RYear = {
  lines(scrubbed).head.split(",").last.toInt
}

type PlayersList = List[List[String]]

def makePlayersList(scrubbed: Scrubbed): PlayersList = {
  scrubbed
    .split("\n")
    .toList
    .tail
    .map(l => l.split(",")
      .toList)
}

type PlrSym = Symbol
type Givee = PlrSym
type Giver = PlrSym

case class GiftPair(givee: Givee, giver: Giver)

type PName = String
type GiftHist = Vector[GiftPair]

case class Player(pName: PName, giftHist: GiftHist)

type PlayerLine = List[String]
type PlayerKV = (PlrSym, Player)

def makePlayerKV(kv: PlayerLine): PlayerKV =
  kv match {
    case List(s, pn, ge, gr) =>
      val gp = GiftPair(Symbol(ge), Symbol(gr))
      val plr = Player(pn, Vector(gp))
      (Symbol(s), plr)
  }

type PlayersKVList = List[(PlrSym, Player)]

def makePlayerKVList(playersList: PlayersList): PlayersKVList =
  playersList.map(kvp => makePlayerKV(kvp))

val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"

getRosterName(ss)
getRosterYear(ss)
makePlayersList(ss)
makePlayerKVList(makePlayersList(ss))
