package uk.co.bbc.dojo.exercise

class MasterCodeballSpec extends CodemonBaseSpec {
  describe("#10 - The ultimate Codeball that supports the king of the operations should") {
    it("a. flatten Empty Master Codeballs") {
      MasterCodeball.flatten(EmptyMasterBall) shouldBe EmptyMasterBall
      MasterCodeball.flatten(OccupiedMasterBall(EmptyMasterBall)) shouldBe EmptyMasterBall
    }

    it("b. flatten nested Occupied Master Codeballs") {
      val innerMaster = OccupiedMasterBall("The Eiffel Tower")
      MasterCodeball.flatten(OccupiedMasterBall(innerMaster)) shouldBe innerMaster
    }

    it("c. only handle one level of nesting") {
      val nestedCore = OccupiedMasterBall(OccupiedMasterBall("The Rosetta Stone"))
      MasterCodeball.flatten(OccupiedMasterBall(nestedCore)) shouldBe nestedCore
    }
  }

  describe("#11 - Onwards to the Monad should") {
    it("a. allow us to use 'pure' (aka Unit etc.) to create a new wrapped value") {
      object ATonOfConcrete
      MasterCodeball.pure(ATonOfConcrete) shouldBe OccupiedMasterBall(ATonOfConcrete)
    }

    it("b. do nothing if we flatMap over an Empty Master Codeball") {
      MasterCodeball.flatMap(EmptyMasterBall)(_ => OccupiedMasterBall("Doesn't matter"))
    }

    it("c. Allow us to keep the context on an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(x => MasterCodeball.pure(x)) shouldBe secretMonad
    }

    it("d. Allow us to empty out an Occupied Codeball") {
      val secretMonad = MasterCodeball.pure("Magical Contents!")
      MasterCodeball.flatMap(secretMonad)(x => EmptyMasterBall) shouldBe EmptyMasterBall
    }
  }
}
