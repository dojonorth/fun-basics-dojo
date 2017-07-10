package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class MasterCodeballSpec extends CodemonBaseSpec {
  describe("#10 - The ultimate Codeball that supports the king of the operations should") {
    it("a. flatten Empty Master Codeballs") {
      MasterCodeball.flatten(EmptyCodeball) shouldBe EmptyCodeball
      MasterCodeball.flatten(OccupiedCodeball(EmptyCodeball)) shouldBe EmptyCodeball
    }

    it("b. flatten nested Occupied Master Codeballs") {
      val innerMaster = OccupiedCodeball("The Eiffel Tower")
      MasterCodeball.flatten(OccupiedCodeball(innerMaster)) shouldBe innerMaster
    }

    it("c. only handle one level of nesting") {
      val nestedCore = OccupiedCodeball(OccupiedCodeball("The Rosetta Stone"))
      MasterCodeball.flatten(OccupiedCodeball(nestedCore)) shouldBe nestedCore
    }
  }

  describe("#11 - Onwards to the Monad should") {
    it("a. allow us to use 'pure' (aka Unit etc.) to create a new wrapped value") {
      object ATonOfConcrete
      MasterCodeball.pure(ATonOfConcrete) shouldBe OccupiedCodeball(ATonOfConcrete)
    }

    it("b. do nothing if we flatMap over an Empty Master Codeball") {
      MasterCodeball.flatMap(EmptyCodeball)(_ => OccupiedCodeball("Doesn't matter"))
    }

    it("c. Allow us to keep the context on an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(x => MasterCodeball.pure(x)) shouldBe secretMonad
    }

    it("d. Allow us to empty out an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(x => EmptyCodeball) shouldBe EmptyCodeball
    }
  }
}
