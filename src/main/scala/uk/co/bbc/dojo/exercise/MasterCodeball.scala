package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Monad

object MasterCodeball extends Monad[MasterCodeball] {
  override def flatten[A](masterBall: MasterCodeball[MasterCodeball[A]]): MasterCodeball[A] = masterBall match {
    case OccupiedMasterBall(innerCodeball: OccupiedMasterBall[A]) => innerCodeball
    case _ => EmptyMasterBall
  }
}

trait MasterCodeball[+A] {
  def get: A
}

object EmptyMasterBall extends MasterCodeball[Nothing] {
  override def get: Nothing = throw new RuntimeException("Cannot grab the contents of an Empty master Ball")
}

case class OccupiedMasterBall[A](contents: A) extends MasterCodeball[A] {
  override def get: A = contents
}
