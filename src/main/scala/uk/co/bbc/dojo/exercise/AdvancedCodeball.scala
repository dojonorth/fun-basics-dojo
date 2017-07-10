package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Functor

object AdvancedCodeball extends Functor[Codeball] {
  override def pure[A](contents: A): Codeball[A] = OccupiedCodeball(contents)

  override def map[A, B](codeball: Codeball[A])(f: (A) => B): Codeball[B] = codeball match {
    case EmptyCodeball => EmptyCodeball
    case OccupiedCodeball(contents) => OccupiedCodeball(f(contents))
  }
}
