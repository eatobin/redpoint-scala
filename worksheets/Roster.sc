import redpoint.RosterStringCheck._

type RawString = String
type Scrubbed = String
type ErrorString = String
type ErrorOrScrubbed = Either[ErrorString, Scrubbed]
type RosterAsStringList = List[String]
type PlayersAsStringList = List[String]
type PlayersAsListOfSymbolsLists = List[List[String]]
type PlayerAsListOfSymbols = List[String]
type RName = String
type RYear = Int
type PlrSym = Symbol
type Givee = PlrSym
type Giver = PlrSym
type PlayersList = List[List[String]]
type PName = String
type GiftHist = Vector[GiftPair]

case class GiftPair(givee: Givee, giver: Giver)

// Given a scrubbed return the roster name
def getRosterName(scrubbed: Scrubbed): RName = {
  lines(scrubbed).head.split(",").head
}

val rs: RawString = "The Beatles, 2014\nRinSta, Ringo Starr, JohLen, GeoHar\nJohLen, John Lennon, PauMcc, RinSta\nGeoHar, George Harrison, RinSta, PauMcc\nPauMcc, Paul McCartney, GeoHar, JohLen\n"
getRosterName(scrub(rs))

// Given a scrubbed return the roster year
def getRosterYear(scrubbed: Scrubbed): RYear = {
  lines(scrubbed).head.split(",").last.toInt
}

getRosterYear(scrub(rs))



def makePlayersLists(scrubbed: Scrubbed): PlayersList = {
  scrubbed
    .split("\n")
    .toList
    .tail
    .map(l => l.split(",")
      .toList)
}

val playersList = makePlayersLists(scrub(rs))





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

type PlayersMap = Map[PlrSym, Player]

def makePlayerKVMap(playersKVList: PlayersKVList): PlayersMap =
  playersKVList.toMap

val makePlayersMap: PlayersList => PlayersMap =
  makePlayerKVMap _ compose makePlayerKVList

val ss = "The Beatles,2014\nRinSta,Ringo Starr,JohLen,GeoHar\nJohLen,John Lennon,PauMcc,RinSta\nGeoHar,George Harrison,RinSta,PauMcc\nPauMcc,Paul McCartney,GeoHar,JohLen"

getRosterName(ss)
getRosterYear(ss)
makePlayersLists(ss)
makePlayersMap(makePlayersLists(ss))
makePlayersMap(makePlayersLists(ss))('PauMcc)
