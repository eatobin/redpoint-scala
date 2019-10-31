package redpoint

object Players {
  def getPlayer(players: Players, playerKey: PlayerKey): Player =
    players(playerKey)

  //  def setGiftPair(giftHistory: GiftHistory, giftYear: GiftYear, giftPair: GiftPair): GiftHistory = {
  //    giftHistory.updated(giftYear, giftPair)
  //  }
}


//(ns clojure-redpoint.players
//(:require [clojure-redpoint.gift-history :as gh]
//[clojure-redpoint.player]
//[clojure.spec.alpha :as s]
//[orchestra.spec.test :as ostest]))
//
//(s/def :unq/players (s/map-of ::gh/player-key :unq/player))
//
//(defn get-player
//"Returns a player given players and a player-key"
//[players plr-key]
//(players plr-key))
//(s/fdef get-player
//:args (s/cat :players :unq/players
//:plr-key ::gh/player-key)
//:ret (s/or :found :unq/player
//:not-found nil?))
//
//(defn set-player
//"Sets a player given players, a player-key and a new player"
//[players plr-key player]
//(assoc players plr-key player))
//(s/fdef set-player
//:args (s/cat :players :unq/players
//:plr-key ::gh/player-key
//:player :unq/player)
//:ret :unq/players)
//
//(ostest/instrument)
