package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class CodeballSpec extends CodemonBaseSpec {

  describe("#2 - Our basic Codeball") {
    it("a. will return the contained Codemon if one is inside") {
      val freeRaabychu = RaabyChu
      val imprisonedRaabyChu = OccupiedCodeball(freeRaabychu)

      imprisonedRaabyChu.chooseYou shouldBe RaabyChu
    }

    it("b. will throw an exception if we try and extract a Codemon from an empty codeball") {
      intercept[RuntimeException] {
        EmptyCodeball.chooseYou
      }
    }
  }

  describe("#3 - Our codeball features a map (functor) operator") {
    it("a. doesn't do anything when applied to an empty ball") {
      EmptyCodeball.map(codemon => Codemon.evolve(codemon)) shouldBe EmptyCodeball
    }

    it("b. applies the function to the Codemon inside an occupied ball") {
      val imprisonedRusa = OccupiedCodeball(Rusa)

      imprisonedRusa.map(Codemon.evolve) shouldBe OccupiedCodeball(Sikachu)
    }
  }
}
