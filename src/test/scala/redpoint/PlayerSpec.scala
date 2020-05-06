package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Player._

class PlayerSpec extends AnyFlatSpec {

  private val player: Player = Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar)))

  "A Player" should "return its playerName" in {
    assert(player.playerName == "Ringo Starr")
  }

  it should "return its giftHistory" in {
    assert(player.giftHistory == Vector(GiftPair('JohLen, 'GeoHar)))
  }

  it should "return an updated giftHistory" in {
    assert(setGiftHistory(player, Vector(GiftPair('nope, 'yup))) ==
      Player("Ringo Starr", Vector(GiftPair('nope, 'yup))))
  }

  it should "return an extended giftHistory in player" in {
    assert(addYearPlayer(player, 'mee) ==
      Player("Ringo Starr", Vector(GiftPair('JohLen, 'GeoHar), GiftPair('mee, 'mee))))
  }
}
