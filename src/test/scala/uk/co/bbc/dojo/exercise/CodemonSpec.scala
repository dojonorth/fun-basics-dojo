package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class CodemonSpec extends CodemonBaseSpec {

  describe("#1 - Our exciting new Codemon datatype") {
    ignore("a. should always return itself with the identity morphism") {
      Codemon.identity(Rusa) shouldBe Rusa
      Codemon.identity(RaabyChu) shouldBe RaabyChu
    }

    ignore("b. should feature a basic evolution morphism") {
      Codemon.evolve(Rusa) shouldBe Sikachu
    }

    ignore("c. should not evolve past its final form") {
      Codemon.evolve(RaabyChu) shouldBe RaabyChu
    }

    ignore("d. should support multiple consecutive evolutions (composability)") {
      Codemon.evolve(Codemon.evolve(Rusa)) shouldBe RaabyChu
    }
  }
}
