package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class ImprovedWildCodemonCaptureSpec extends CodemonBaseSpec {
  describe("#12 - Now with the unlimited power of the Monad we can") {
    it("a. write map in terms of flatMap and pure") {
      ImprovedWildCodemonCapture.mapViaFlatmap(EmptyMasterBall)(x => "cat") shouldBe EmptyMasterBall
      ImprovedWildCodemonCapture.mapViaFlatmap(OccupiedMasterBall(100))(x => x + 7) shouldBe OccupiedMasterBall(107)
      ImprovedWildCodemonCapture.mapViaFlatmap(OccupiedMasterBall("Here"))(_ => "Gone") shouldBe OccupiedMasterBall("Gone")
    }

    it("b. sequence theImprovedWildCodemonCapture capture operations without causing crazy levels of nesting") {
      ImprovedWildCodemonCapture.captureLifecyclewithMonads(() => true) shouldBe OccupiedMasterBall(RaabyChu)
    }
  }
}
