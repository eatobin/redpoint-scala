package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Player._

class PlayerSpec extends AnyFlatSpec {

  private val player: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))

  "A Player" should "return its playerName" in {
    assert(player.playerName == "Ringo Starr")
  }

  it should "return its giftHistory" in {
    assert(player.giftHistory == Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))
  }

  it should "return an updated giftHistory" in {
    assert(setGiftHistory(player, Vector(GiftPair(Symbol("nope"), Symbol("yup")))) ==
      Player("Ringo Starr", Vector(GiftPair(Symbol("nope"), Symbol("yup")))))
  }

  it should "return an extended giftHistory in player" in {
    assert(addYearPlayer(player, Symbol("mee")) ==
      Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar")), GiftPair(Symbol("mee"), Symbol("mee")))))
  }
}
