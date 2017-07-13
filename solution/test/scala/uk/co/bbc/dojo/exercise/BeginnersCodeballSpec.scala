package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class BeginnersCodeballSpec extends CodemonBaseSpec {

  describe("#2 - Our beginners Codeball") {
    it("a. will return the contained Codemon if one is inside") {
      val freeRaabychu = RaabyChu
      val imprisonedRaabyChu = OccupiedBeginnersCodeball(freeRaabychu)

      imprisonedRaabyChu.codemon shouldBe RaabyChu
    }

    it("b. will throw an exception if we try and extract a Codemon from an empty codeball") {
      intercept[RuntimeException] {
        EmptyBeginnersCodeball.codemon
      }
    }
  }

  describe("#3 - Our beginners codeball features a map (functor) operator") {
    it("a. doesn't do anything when applied to an empty ball") {
      EmptyBeginnersCodeball.map(codemon => Codemon.evolve(codemon)) shouldBe EmptyBeginnersCodeball
    }

    it("b. applies the function to the Codemon inside an occupied ball") {
      val imprisonedRusa = OccupiedBeginnersCodeball(Rusa)

      imprisonedRusa.map(Codemon.evolve) shouldBe OccupiedBeginnersCodeball(Sikachu)
    }
  }
}
