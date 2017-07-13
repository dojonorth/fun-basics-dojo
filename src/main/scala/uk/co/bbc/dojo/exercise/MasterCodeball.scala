package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.concepts.Monad

object MasterCodeball extends Monad[Codeball] {
  override def pure[A](value: A): Codeball[A] = ???

  override def map[A, B](codeball: Codeball[A])(f: (A) => B): Codeball[B] = ???

  override def flatMap[A, B](codeball: Codeball[A])(f: A => Codeball[B]): Codeball[B] = ???

  def flatten[A](codeball: Codeball[Codeball[A]]): Codeball[A] = ???
}
