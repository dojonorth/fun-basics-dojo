package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Functor

object MasterCodeball extends Functor[MasterCodeball] {
  override def apply[A](contents: A): MasterCodeball[A] = OccupiedMasterBall(contents)

  override def map[A, B](masterCodeBall: MasterCodeball[A])(f: (A) => B): MasterCodeball[B] = masterCodeBall match {
    case EmptyMasterBall => EmptyMasterBall
    case OccupiedMasterBall(contents) => OccupiedMasterBall(f(contents))
  }
}

//TODO: Comment on +A
//TODO: All this stuff is just about getting the types down.
trait MasterCodeball[+A] {
  def get: A
}

object EmptyMasterBall extends MasterCodeball[Nothing] {
  //TODO: Uninspired name - the vaneer over this just being an Option is obvious coming off
  override def get: Nothing = throw new RuntimeException("Cannot grab the contents of an Empty Master Ball")
}

case class OccupiedMasterBall[A](contents: A) extends MasterCodeball[A] {
  override def get: A = contents
}
