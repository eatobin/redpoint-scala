package redpoint

object Rules {
  def giveeNotSelf(playerKey: PlayerKey, givee: Givee): Boolean =
    playerKey != givee
}
