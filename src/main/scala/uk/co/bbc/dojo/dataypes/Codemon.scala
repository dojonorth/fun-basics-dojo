package uk.co.bbc.dojo.dataypes

// TODO: Better to make evolve etc functions defined in a companion object? More idiomatic?

//Category
sealed trait Codemon {
  // TODO: Need to put this here?
  final val identity: Codemon = this

  // Basic morphism (by default, we assume that a Codemon cannot evolve - any attempt causes nothing to happen)
  val evolve: Codemon = identity
}

//Objects
object RaabyChu extends Codemon
object Sikachu extends Codemon { override val evolve: Codemon = RaabyChu }
object Rusa extends Codemon { override val evolve: Codemon = Sikachu }
