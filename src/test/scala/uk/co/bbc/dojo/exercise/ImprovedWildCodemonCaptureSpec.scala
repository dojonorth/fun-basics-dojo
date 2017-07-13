package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class ImprovedWildCodemonCaptureSpec extends CodemonBaseSpec {
  describe("#13 - Now with the unlimited power of the Monad we can") {
    it("a. write map in terms of flatMap and pure") {
      ImprovedWildCodemonCapture.mapViaFlatmap(EmptyCodeball)(x => "cat") shouldBe EmptyCodeball
      ImprovedWildCodemonCapture.mapViaFlatmap(OccupiedCodeball(100))(x => x + 7) shouldBe OccupiedCodeball(107)
      ImprovedWildCodemonCapture.mapViaFlatmap(OccupiedCodeball("Here"))(_ => "Gone") shouldBe OccupiedCodeball("Gone")
    }

    it("b. sequence theImprovedWildCodemonCapture capture operations without causing crazy levels of nesting") {
      ImprovedWildCodemonCapture.captureLifecyclewithMonads(() => true) shouldBe OccupiedCodeball(RaabyChu)
    }
  }

  describe("Optional Extra") {
    it("Rewrite the capture lifecycle using a for-comprehension") {
      ImprovedWildCodemonCapture.captureLifecycleUsingAForComprehension(() => true) shouldBe OccupiedCodeball(RaabyChu)
    }
  }
}
