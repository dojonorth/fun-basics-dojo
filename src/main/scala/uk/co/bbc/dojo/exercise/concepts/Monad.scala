package uk.co.bbc.dojo.exercise.concepts

trait Monad[T[_]] {
  def pure[A](value: A): T[A]

  def flatMap[A, B](x: T[A])(f: A => T[B]): T[B]

  def flatten[A](m: T[T[A]]): T[A] //Not part of the strict definition. But nice as a convenience.
}
