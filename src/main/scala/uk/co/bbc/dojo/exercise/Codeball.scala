package uk.co.bbc.dojo.exercise

// Our thing with (quite dull) functor capability
sealed trait Codeball {
  def codemon: Codemon
  def map(f: Codemon => Codemon): Codeball
}

case class OccupiedCodeball(codemon: Codemon) extends Codeball {
  override def map(f: (Codemon) => Codemon): Codeball = OccupiedCodeball(f(codemon))
}

case object EmptyCodeball extends Codeball {
  override def codemon: Codemon = throw new RuntimeException("Can't retrieve contents of an empty Codeball")

  override def map(f: (Codemon) => Codemon): Codeball = EmptyCodeball
}
