package uk.co.bbc.dojo.exercise

// Identity function and evolve endomorphism
object Codemon {
  def identity(codemon: Codemon): Codemon = codemon

  def evolve(codemon: Codemon): Codemon = codemon match {
    case Rusa => Sikachu
    case Sikachu => RaabyChu
    case RaabyChu => RaabyChu
  }
}

//Object
sealed trait Codemon
case object RaabyChu extends Codemon
case object Sikachu extends Codemon
case object Rusa extends Codemon
