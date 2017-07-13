package uk.co.bbc.dojo.exercise

sealed trait BeginnersCodeball {
  def codemon: Codemon
  def map(f: Codemon => Codemon): BeginnersCodeball
}

case class OccupiedBeginnersCodeball(codemon: Codemon) extends BeginnersCodeball {
  override def map(f: (Codemon) => Codemon): BeginnersCodeball = ???
}

case object EmptyBeginnersCodeball extends BeginnersCodeball {
  override def codemon: Codemon = ??? //Don't do it!

  override def map(f: (Codemon) => Codemon): BeginnersCodeball = ???
}
