package uk.co.bbc.dojo.solution

// Our thing with (quite dull) functor capability
sealed trait CodeBall {
  def chooseYou: Codemon
  def map(f: Codemon => Codemon): CodeBall
}

case class OccupiedCodeball(private val prisoner: Codemon) extends CodeBall {
  override def chooseYou: Codemon = prisoner

  override def map(f: (Codemon) => Codemon): CodeBall = OccupiedCodeball(f(prisoner))
}

case object EmptyCodeball extends CodeBall {
  override def chooseYou: Codemon = throw new RuntimeException("Nobody home - viva la Codemon")

  override def map(f: (Codemon) => Codemon): CodeBall = EmptyCodeball
}
