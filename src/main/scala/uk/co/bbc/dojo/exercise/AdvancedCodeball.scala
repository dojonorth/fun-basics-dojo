package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Functor

object AdvancedCodeball extends Functor[AdvancedCodeball] {
  override def pure[A](contents: A): AdvancedCodeball[A] = OccupiedAdvancedBall(contents)

  override def map[A, B](advnacedCodeBall: AdvancedCodeball[A])(f: (A) => B): AdvancedCodeball[B] = advnacedCodeBall match {
    case EmptyAdvancedBall => EmptyAdvancedBall
    case OccupiedAdvancedBall(contents) => OccupiedAdvancedBall(f(contents))
  }
}

//TODO: Comment on +A
trait AdvancedCodeball[+A] {
  def get: A
}

object EmptyAdvancedBall extends AdvancedCodeball[Nothing] {
  override def get: Nothing = throw new RuntimeException("Cannot grab the contents of an Empty Advanced Ball")
}

case class OccupiedAdvancedBall[A](contents: A) extends AdvancedCodeball[A] {
  override def get: A = contents
}
