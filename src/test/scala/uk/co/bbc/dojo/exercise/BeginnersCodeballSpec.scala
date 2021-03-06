package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class BeginnersCodeballSpec extends CodemonBaseSpec {

  describe("#2 - Our beginner's Codeball") {
    ignore("a. will return the contained Codemon if one is inside") {
      val freeRaabychu = RaabyChu
      val imprisonedRaabyChu = OccupiedBeginnersCodeball(freeRaabychu)

      imprisonedRaabyChu.codemon shouldBe RaabyChu
    }

    ignore("b. will throw an exception if we try and extract a Codemon from an empty codeball") {
      intercept[RuntimeException] {
        EmptyBeginnersCodeball.codemon
      }
    }
  }

  describe("#3 - Our beginner's codeball features a map (functor) operator") {
    ignore("a. doesn't do anything when applied to an empty ball") {
      EmptyBeginnersCodeball.map(codemon => Codemon.evolve(codemon)) shouldBe EmptyBeginnersCodeball
    }

    ignore("b. applies the function to the Codemon inside an occupied ball") {
      val imprisonedRusa = OccupiedBeginnersCodeball(Rusa)

      imprisonedRusa.map(Codemon.evolve) shouldBe OccupiedBeginnersCodeball(Sikachu)
    }
  }
}
