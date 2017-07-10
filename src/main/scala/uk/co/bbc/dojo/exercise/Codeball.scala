package uk.co.bbc.dojo.exercise


//TODO: Comment on +A
trait Codeball[+A] {
  def get: A
}

object EmptyCodeball extends Codeball[Nothing] {
  override def get: Nothing = throw new RuntimeException("Cannot grab the contents of an Empty Advanced Ball")
}

case class OccupiedCodeball[A](contents: A) extends Codeball[A] {
  override def get: A = contents
}
