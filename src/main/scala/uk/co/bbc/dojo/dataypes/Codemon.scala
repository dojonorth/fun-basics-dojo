package uk.co.bbc.dojo.dataypes

// Identity and sample evolve morphism
object Codemon {
  def identity(codemon: Codemon): Codemon = codemon

  def evolve(codemon: Codemon): Codemon = codemon match {
    case Rusa => Sikachu
    case Sikachu => RaabyChu
    case RaabyChu => RaabyChu
  }
}

//Category
sealed trait Codemon

//Objects
object RaabyChu extends Codemon
object Sikachu extends Codemon
object Rusa extends Codemon
