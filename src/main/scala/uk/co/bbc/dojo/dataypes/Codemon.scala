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
case object RaabyChu extends Codemon
case object Sikachu extends Codemon
case object Rusa extends Codemon
