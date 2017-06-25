package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Functor

object MasterCodeBall extends Functor[MasterCodeBall] {
  override def apply[A](contents: A): MasterCodeBall[A] = OccupiedMasterBall(contents)

  override def map[A, B](masterCodeBall: MasterCodeBall[A])(f: (A) => B): MasterCodeBall[B] = masterCodeBall match {
    case EmptyMasterBall => EmptyMasterBall
    case OccupiedMasterBall(contents) => OccupiedMasterBall(f(contents))
  }
}

//TODO: Comment on +A
//TODO: All this stuff is just about getting the types down.
trait MasterCodeBall[+A] {
  def get: A
}

object EmptyMasterBall extends MasterCodeBall[Nothing] {
  //TODO: Uninspired name - the vaneer over this just being an Option is obvious coming off
  override def get: Nothing = throw new RuntimeException("Cannot grab the contents of an Empty Master Ball")
}

case class OccupiedMasterBall[A](contents: A) extends MasterCodeBall[A] {
  override def get: A = contents
}
