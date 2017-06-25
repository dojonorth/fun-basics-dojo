package uk.co.bbc.dojo.exercise

import WildCodemonCapture._

class WildCodemonCaptureSpec extends CodemonBaseSpec {
  private val successfulRandomEvent = () => true
  private val failedRandomEvent = () => false

  describe("#6 - Throwing a codeball") {
    it("a. should catch a Rusa if the random function works out") {
      throwCodeball(successfulRandomEvent) shouldBe OccupiedMasterBall(Rusa)
    }

    it("b. should return an empty codeball if we're unlucky") {
      throwCodeball(failedRandomEvent) shouldBe EmptyMasterBall
    }
  }

  describe("#7 - Baiting a trap") {
    it("a. should use the Rusa as bait to catch a Sikachu successfully") {
      baitTrap(Rusa) shouldBe OccupiedMasterBall(Sikachu)
    }

    it("b. should return an empty Codeball if any other Codemon is used as bait") {
      baitTrap(RaabyChu) shouldBe EmptyMasterBall
    }
  }

  describe("#8 - Risky fast evolution") {
    it("a. should instantly evolve a Codemon if we get lucky") {
      fastEvolveCodemon(successfulRandomEvent)(Sikachu) shouldBe OccupiedMasterBall(RaabyChu)
      fastEvolveCodemon(successfulRandomEvent)(Rusa) shouldBe OccupiedMasterBall(Sikachu)
    }

    it("b. should kill off the Codemon and return an empty ball if we're unlucky") {
      fastEvolveCodemon(failedRandomEvent)(Sikachu) shouldBe EmptyMasterBall
      fastEvolveCodemon(failedRandomEvent)(Rusa) shouldBe EmptyMasterBall
    }

    describe("#9 - Putting it all together") {
      it("a. should let us sequence together the various operations") {
        captureLifecycle(successfulRandomEvent) shouldBe OccupiedMasterBall(OccupiedMasterBall(OccupiedMasterBall(RaabyChu)))
      }

      it("b. should deal with the failure case without blowing up") {
        captureLifecycle(failedRandomEvent) shouldBe EmptyMasterBall
      }
    }
  }
}
