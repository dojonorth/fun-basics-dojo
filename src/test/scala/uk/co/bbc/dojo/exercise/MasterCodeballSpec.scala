package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class MasterCodeballSpec extends CodemonBaseSpec {
  describe("#10 - The ultimate Codeball that supports the king of the operations should") {
    ignore("a. flatten Empty Master Codeballs") {
      MasterCodeball.flatten(EmptyCodeball) shouldBe EmptyCodeball
      MasterCodeball.flatten(OccupiedCodeball(EmptyCodeball)) shouldBe EmptyCodeball
    }

    ignore("b. flatten nested Occupied Master Codeballs") {
      val innerMaster = OccupiedCodeball("The Eiffel Tower")
      MasterCodeball.flatten(OccupiedCodeball(innerMaster)) shouldBe innerMaster
    }

    ignore("c. only handle one level of nesting") {
      val nestedCore = OccupiedCodeball(OccupiedCodeball("The Rosetta Stone"))
      MasterCodeball.flatten(OccupiedCodeball(nestedCore)) shouldBe nestedCore
    }
  }

  describe("#11 - Onwards to the Monad should") {
    ignore("a. allow us to use 'pure' (aka Unit etc.) to create a new wrapped value") {
      object ATonOfConcrete
      MasterCodeball.pure(ATonOfConcrete) shouldBe OccupiedCodeball(ATonOfConcrete)
    }

    ignore("b. do nothing if we flatMap over an Empty Master Codeball") {
      MasterCodeball.flatMap(EmptyCodeball)(_ => OccupiedCodeball("Doesn't matter"))
    }

    ignore("c. Allow us to keep the context on an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(x => MasterCodeball.pure(x)) shouldBe secretMonad
    }

    ignore("d. Allow us to empty out an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(x => EmptyCodeball) shouldBe EmptyCodeball
    }
  }

  describe("#12 - Proving the mathematical properties hold up") {
    val value = 7
    val monad = MasterCodeball.pure(value)
    def fn(x: Int) = OccupiedCodeball(x + 6)
    def fn2(x: Int) = OccupiedCodeball(x * 9)

    ignore("Satisfies left identity") {
      MasterCodeball.flatMap(MasterCodeball.pure(value))(fn) shouldBe fn(value)
    }

    ignore("Satisfies right identity") {
      MasterCodeball.flatMap(monad)(MasterCodeball.pure) shouldBe monad
    }

    ignore("Satisfies associativity") {
      MasterCodeball.flatMap(MasterCodeball.flatMap(monad)(fn))(fn2) shouldBe MasterCodeball.flatMap(monad)(x => MasterCodeball.flatMap(fn(x))(fn2))
    }
  }
}
