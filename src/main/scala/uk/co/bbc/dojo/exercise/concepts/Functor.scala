package uk.co.bbc.dojo.exercise.concepts

trait Functor[T[_]] {
  def apply[A](value: A): T[A]
  def map[A, B](x: T[A])(f: A => B): T[B]
}
