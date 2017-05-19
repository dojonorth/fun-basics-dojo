package uk.co.bbc.dojo.dataypes

sealed trait CodeBall {
  def chooseYou: Codemon
  def map(f: Codemon => Codemon): CodeBall
}

case class OccupiedCodeball(private val prisoner: Codemon) extends CodeBall {
  override def chooseYou: Codemon = prisoner

  override def map(f: (Codemon) => Codemon): CodeBall = OccupiedCodeball(f(prisoner))
}

object EmptyCodeball extends CodeBall {
  override def chooseYou: Codemon = throw new RuntimeException("Nobody home - viva la Codemon")

  override def map(f: (Codemon) => Codemon): CodeBall = EmptyCodeball
}