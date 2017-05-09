package uk.co.bbc.dojo.dataypes

case class NeoCodemonCentre[A](contents: A*) {
  def map[B](f: A => B): NeoCodemonCentre[B] = NeoCodemonCentre[B](contents.map(f): _*)
}
