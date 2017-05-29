package uk.co.bbc.dojo.exercise

// Our thing with (quite dull) functor capability
sealed trait CodeBall {
  def chooseYou: Codemon
  def map(f: Codemon => Codemon): CodeBall
}

case class OccupiedCodeball(private val prisoner: Codemon) extends CodeBall {
  override def chooseYou: Codemon = ??? //TODO: 2a

  override def map(f: (Codemon) => Codemon): CodeBall = ??? //TODO: 3b
}

case object EmptyCodeball extends CodeBall {
  override def chooseYou: Codemon = ??? //TODO: 2b.

  override def map(f: (Codemon) => Codemon): CodeBall = ??? //TODO: 3a
}
