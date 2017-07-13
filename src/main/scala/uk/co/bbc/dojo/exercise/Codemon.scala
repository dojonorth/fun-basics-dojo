package uk.co.bbc.dojo.exercise

// Identity function and evolve endomorphism
object Codemon {
  def identity(codemon: Codemon): Codemon = ??? //Challenging!

  def evolve(codemon: Codemon): Codemon = ???
}

//Object
sealed trait Codemon
case object RaabyChu extends Codemon
case object Sikachu extends Codemon
case object Rusa extends Codemon
