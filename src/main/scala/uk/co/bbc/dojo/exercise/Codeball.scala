package uk.co.bbc.dojo.exercise

// Our thing with (quite dull) functor capability
sealed trait Codeball {
  def chooseYou: Codemon
  def map(f: Codemon => Codemon): Codeball
}

case class OccupiedCodeball(private val prisoner: Codemon) extends Codeball {
  override def chooseYou: Codemon = prisoner

  override def map(f: (Codemon) => Codemon): Codeball = OccupiedCodeball(f(prisoner))
}

case object EmptyCodeball extends Codeball {
  override def chooseYou: Codemon = throw new RuntimeException("Can't retrieve contents of an empty Codeball")

  override def map(f: (Codemon) => Codemon): Codeball = EmptyCodeball
}
