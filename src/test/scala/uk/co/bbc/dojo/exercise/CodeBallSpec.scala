package uk.co.bbc.dojo.exercise

class CodeBallSpec extends CodemonBaseSpec {

  describe("#2 - Our tool of Codemon oppression") {
    ignore("a. will return the contained Codemon if one is inside") {
      val freeRaabychu = RaabyChu
      val imprisonedRaabyChu = OccupiedCodeball(freeRaabychu)

      imprisonedRaabyChu.chooseYou shouldBe RaabyChu
    }

    ignore("b. will throw an exception if we try and extract a Codemon from an empty ball") {
      intercept[RuntimeException] {
        EmptyCodeball.chooseYou
      }
    }
  }

  describe("#3 - Our Codeball features a map (functor) operator") {
    ignore("a. doesn't do anything when applied to an empty Codeball") {
      EmptyCodeball.map(codemon => Codemon.evolve(codemon)) shouldBe EmptyCodeball
    }

    ignore("b. applies the function to the Codemon inside an occupied Codeball") {
      val imprisonedRusa = OccupiedCodeball(Rusa)

      imprisonedRusa.map(Codemon.evolve) shouldBe OccupiedCodeball(Sikachu)
    }
  }
}
