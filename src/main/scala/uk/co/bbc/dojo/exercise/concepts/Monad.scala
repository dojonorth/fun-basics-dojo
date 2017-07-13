package uk.co.bbc.dojo.exercise.concepts

import scala.language.higherKinds

trait Monad[T[_]] extends Functor[T]{
  def pure[A](value: A): T[A]

  def flatMap[A, B](x: T[A])(f: A => T[B]): T[B]
}
