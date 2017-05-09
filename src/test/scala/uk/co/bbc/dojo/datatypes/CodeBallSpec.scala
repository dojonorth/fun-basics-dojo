package uk.co.bbc.dojo.datatypes

import uk.co.bbc.dojo.dataypes._

class CodeBallSpec extends CodemonBaseSpec {

  describe("#2 - Our tool of Codemon oppression") {
    it("will return the contained Codemon if one is inside") {
      val freeRaabychu = RaabyChu
      val imprisonedRaabyChu = OccupiedCodeball(freeRaabychu)

      imprisonedRaabyChu.chooseYou shouldBe RaabyChu
    }

    it("will throw an exception if we try and extract a Codemon from an empty ball") {
      intercept[RuntimeException] {
        EmptyCodeball.chooseYou
      }
    }
  }

  describe("#3 - Our Codeball features a map (functor) operator") {
    it("doesn't do anything when applied to an empty Codeball") {
      EmptyCodeball.map(codemon => codemon.evolve) shouldBe EmptyCodeball
    }

    it("applies the function to the Codemon inside an occupied Codeball") {
      val imprisonedRusa = OccupiedCodeball(Rusa)

      imprisonedRusa.map(_.evolve) shouldBe OccupiedCodeball(Sikachu)
    }

    it("allows us to evolve a mixture of occupied and empty codeballs without worrying about whether they're occupied or not") {
      val todaysTrappings = Seq(OccupiedCodeball(RaabyChu), EmptyCodeball, OccupiedCodeball(Rusa), OccupiedCodeball(Sikachu), EmptyCodeball)

      //TODO: Say how it'd be nice to do this in one step. Maybe make the list type ourselves too?
      val bulkEvolution = todaysTrappings.map(x => x.map(_.evolve))

      bulkEvolution shouldBe Seq(OccupiedCodeball(RaabyChu), EmptyCodeball, OccupiedCodeball(Sikachu), OccupiedCodeball(RaabyChu), EmptyCodeball)
    }
  }
}
