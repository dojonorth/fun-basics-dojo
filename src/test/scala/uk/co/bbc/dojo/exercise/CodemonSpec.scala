package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise._

class CodemonSpec extends CodemonBaseSpec {

  describe("#1 - Our exciting new Codemon datatype") {
    it("should always return itself with the identity morphism") {
      Codemon.identity(Rusa) shouldBe Rusa
      Codemon.identity(RaabyChu) shouldBe RaabyChu
    }

    it("should feature a basic evolution morphism") {
      Codemon.evolve(Sikachu) shouldBe RaabyChu
    }

    it("should not evolve past its final form") {
      Codemon.evolve(RaabyChu) shouldBe RaabyChu
    }

    it("should support multiple evolutions (associative law)") {
      Codemon.evolve(Codemon.evolve(Rusa)) shouldBe RaabyChu
    }
  }
}
