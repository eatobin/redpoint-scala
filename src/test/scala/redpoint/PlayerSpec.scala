package redpoint

import org.scalatest.flatspec.AnyFlatSpec
import redpoint.Player._

class PlayerSpec extends AnyFlatSpec {

  private val player: Player = Player("Ringo Starr", Vector(GiftPair(Symbol("JohLen"), Symbol("GeoHar"))))

  "A Player" should "return an updated giftHistory" in {
    assert(playerUpdateGiftHistory(player, Vector(GiftPair(Symbol("nope"), Symbol("yup")))) ==
      Player("Ringo Starr", Vector(GiftPair(Symbol("nope"), Symbol("yup")))))
  }
}
