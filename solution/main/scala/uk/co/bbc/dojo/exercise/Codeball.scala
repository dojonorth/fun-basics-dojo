package uk.co.bbc.dojo.exercise


//Note the covariant '+' see http://docs.scala-lang.org/tutorials/tour/variances.html for more detail
trait Codeball[+A] {
  def get: A

  //**From hereon down only needed for the optional extra for-comprehension bit - don't copy otherwise **
  def map[B](f: A => B): Codeball[B] = AdvancedCodeball.map(this)(f)
  def flatMap[B](f: A => Codeball[B]): Codeball[B] = MasterCodeball.flatMap(this)(f)
}

object EmptyCodeball extends Codeball[Nothing] {
  override def get: Nothing = throw new RuntimeException("Cannot grab the contents of an Empty Ball")
}

case class OccupiedCodeball[A](contents: A) extends Codeball[A] {
  override def get: A = contents
}
