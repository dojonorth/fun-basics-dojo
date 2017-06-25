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
}
