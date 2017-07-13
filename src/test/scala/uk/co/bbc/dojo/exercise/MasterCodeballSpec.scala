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
      MasterCodeball.flatMap(EmptyCodeball)(_ => OccupiedCodeball("Doesn't matter")) shouldBe EmptyCodeball
    }

    ignore("c. Allow us to alter the contents of an Occupied Masterball without introducing additional nesting") {
      def appendWorks(s: String) = MasterCodeball.pure(s + " works!")
      val secretMonad = MasterCodeball.pure("It")
      MasterCodeball.flatMap(secretMonad)(appendWorks) shouldBe MasterCodeball.pure("It works!")
    }

    ignore("d. Allow us to empty out an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(_ => EmptyCodeball) shouldBe EmptyCodeball
    }
  }

  describe("#12 - Proving the mathematical properties hold up") {
    ignore("Satisfies left identity") {
      fail("Write me")
    }

    ignore("Satisfies right identity") {
      fail("Write me")
    }

    ignore("Satisfies associativity") {
      fail("Write me")
    }
  }
}
