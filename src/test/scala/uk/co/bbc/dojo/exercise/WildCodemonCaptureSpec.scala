package uk.co.bbc.dojo.exercise

import WildCodemonCapture._

class WildCodemonCaptureSpec extends CodemonBaseSpec {
  private val successfulRandomEvent = () => true
  private val failedRandomEvent = () => false

  describe("#4a Throwing a codeball") {
    it("should catch a Rusa if the random function works out") {
      throwCodeball(successfulRandomEvent) shouldBe OccupiedMasterBall(Rusa)
    }

    it("should return an empty codeball if we're unlucky") {
      throwCodeball(failedRandomEvent) shouldBe EmptyMasterBall
    }
  }

  describe("#4b Baiting a trap") {
    it("should use the Rusa as bait to catch a Sikachu successfully") {
      baitTrap(Rusa) shouldBe OccupiedMasterBall(Sikachu)
    }

    it("should return an empty Codeball if any other Codemon is used as bait") {
      baitTrap(RaabyChu) shouldBe EmptyMasterBall
    }
  }

  describe("#4c Risky fast evolution") {
    it("should instantly evolve a Codemon if we get lucky") {
      fastEvolveCodemon(successfulRandomEvent)(Sikachu) shouldBe OccupiedMasterBall(RaabyChu)
      fastEvolveCodemon(successfulRandomEvent)(Rusa) shouldBe OccupiedMasterBall(Sikachu)
    }

    it("should kill off the Codemon and return an empty ball if we're unlucky") {
      fastEvolveCodemon(failedRandomEvent)(Sikachu) shouldBe EmptyMasterBall
      fastEvolveCodemon(failedRandomEvent)(Rusa) shouldBe EmptyMasterBall
    }

    describe("4d Putting it all together") {
      it("should let us sequence together the various operations") {
        captureLifecycle(successfulRandomEvent) shouldBe OccupiedMasterBall(OccupiedMasterBall(OccupiedMasterBall(RaabyChu)))
      }

      it("should deal with the failure case without blowing up") {
        captureLifecycle(failedRandomEvent) shouldBe EmptyMasterBall
      }
    }
  }

    //TODO: We're basically modelling anythng with a failure model. Sequencing.
  // Note at the end this level of nesting is undesirable. Unmanageable. We need something else.
  //Note how we're using the master codeball now isntead of the old one
}
