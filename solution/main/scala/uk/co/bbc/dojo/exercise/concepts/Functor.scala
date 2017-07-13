package uk.co.bbc.dojo.exercise.concepts

import scala.language.higherKinds

trait Functor[T[_]] {
  def map[A, B](x: T[A])(f: A => B): T[B]
}
