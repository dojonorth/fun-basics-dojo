package uk.co.bbc.dojo.exercise

// Our thing with (quite dull) functor capability
sealed trait BeginnersCodeball {
  def codemon: Codemon
  def map(f: Codemon => Codemon): BeginnersCodeball
}

case class OccupiedBeginnersCodeball(codemon: Codemon) extends BeginnersCodeball {
  override def map(f: (Codemon) => Codemon): BeginnersCodeball = OccupiedBeginnersCodeball(f(codemon))
}

case object EmptyBeginnersCodeball extends BeginnersCodeball {
  override def codemon: Codemon = throw new RuntimeException("Can't retrieve contents of an empty Codeball")

  override def map(f: (Codemon) => Codemon): BeginnersCodeball = EmptyBeginnersCodeball
}
