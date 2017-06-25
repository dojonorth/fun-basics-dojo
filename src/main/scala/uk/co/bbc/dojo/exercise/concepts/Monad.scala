package uk.co.bbc.dojo.exercise.concepts

trait Monad[T[_]] {
  def flatten[A](m: T[T[A]]): T[A]
}
