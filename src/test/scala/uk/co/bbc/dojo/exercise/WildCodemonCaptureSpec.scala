package uk.co.bbc.dojo.exercise

import WildCodemonCapture._
import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class WildCodemonCaptureSpec extends CodemonBaseSpec {
  private val successfulRandomEvent = () => true
  private val failedRandomEvent = () => false

  describe("#6 - Throwing a codeball") {
    ignore("a. should catch a Rusa if the random function works out") {
      throwCodeball(successfulRandomEvent) shouldBe OccupiedCodeball(Rusa)
    }

    ignore("b. should return an empty codeball if we're unlucky") {
      throwCodeball(failedRandomEvent) shouldBe EmptyCodeball
    }
  }

  describe("#7 - Baiting a trap") {
    ignore("a. should use the Rusa as bait to catch a Sikachu successfully") {
      baitTrap(Rusa) shouldBe OccupiedCodeball(Sikachu)
    }

    ignore("b. should return an empty Codeball if any other Codemon is used as bait") {
      baitTrap(RaabyChu) shouldBe EmptyCodeball
    }
  }

  describe("#8 - Risky fast evolution") {
    ignore("a. should instantly evolve a Codemon if we get lucky") {
      fastEvolveCodemon(successfulRandomEvent)(Sikachu) shouldBe OccupiedCodeball(RaabyChu)
      fastEvolveCodemon(successfulRandomEvent)(Rusa) shouldBe OccupiedCodeball(Sikachu)
    }

    ignore("b. should kill off the Codemon and return an empty ball if we're unlucky") {
      fastEvolveCodemon(failedRandomEvent)(Sikachu) shouldBe EmptyCodeball
      fastEvolveCodemon(failedRandomEvent)(Rusa) shouldBe EmptyCodeball
    }
  }

  describe("#9 - Putting it all together") {
    ignore("a. should let us sequence together the various operations") {
      captureLifecycle(successfulRandomEvent) shouldBe OccupiedCodeball(OccupiedCodeball(OccupiedCodeball(RaabyChu)))
    }

    ignore("b. should deal with the failure case without blowing up") {
      captureLifecycle(failedRandomEvent) shouldBe EmptyCodeball
    }
  }
}
