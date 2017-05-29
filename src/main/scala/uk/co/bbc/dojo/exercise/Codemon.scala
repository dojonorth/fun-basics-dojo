package uk.co.bbc.dojo.exercise

// Identity and sample evolve morphism
object Codemon {
  def identity(codemon: Codemon): Codemon = ??? //TODO: 1a Complete the identity function.

  def evolve(codemon: Codemon): Codemon = ??? //TODO: 1b - 1d add in the evolve behaviour
}

//Category
sealed trait Codemon

//Objects
case object RaabyChu extends Codemon
case object Sikachu extends Codemon
case object Rusa extends Codemon
