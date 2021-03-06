package uk.co.bbc.dojo.exercise


//Note the covariant '+' see http://docs.scala-lang.org/tutorials/tour/variances.html for more detail
trait Codeball[+A] {
  def get: A
}

object EmptyCodeball extends Codeball[Nothing] {
  override def get: Nothing = ???
}

case class OccupiedCodeball[A](contents: A) extends Codeball[A] {
  override def get: A = ???
}
