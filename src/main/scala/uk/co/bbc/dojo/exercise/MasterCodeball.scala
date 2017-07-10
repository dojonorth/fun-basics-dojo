package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Monad

object MasterCodeball extends Monad[Codeball] {
  override def pure[A](value: A): Codeball[A] = OccupiedCodeball(value)

  //TODO: Fix this.
  override def map[A, B](codeball: Codeball[A])(f: (A) => B): Codeball[B] = AdvancedCodeball.map(codeball)(f) //Just delegate

  override def flatMap[A, B](codeball: Codeball[A])(f: A => Codeball[B]): Codeball[B] = flatten {
    codeball match {
      case EmptyCodeball => EmptyCodeball
      case OccupiedCodeball(value) => pure(f(value))
    }
  }

  override def flatten[A](codeball: Codeball[Codeball[A]]): Codeball[A] = codeball match {
    case OccupiedCodeball(innerCodeball: OccupiedCodeball[A]) => innerCodeball
    case _ => EmptyCodeball
  }

}
