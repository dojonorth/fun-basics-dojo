package uk.co.bbc.dojo.exercise.concepts

import scala.language.higherKinds

trait Monad[T[_]] extends Functor[T]{
  def flatMap[A, B](x: T[A])(f: A => T[B]): T[B]

  def flatten[A](m: T[T[A]]): T[A] //Not part of the definition. But nice as a convenience.
}
