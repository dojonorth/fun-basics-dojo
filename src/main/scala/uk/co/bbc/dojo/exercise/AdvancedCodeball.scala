package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Functor

object AdvancedCodeball extends Functor[Codeball] {
  override def map[A, B](codeball: Codeball[A])(f: (A) => B): Codeball[B] = ???
}
