package uk.co.bbc.dojo.datatypes

import org.scalatest.{FunSpec, Matchers}
import uk.co.bbc.dojo.dataypes._

class CodemonSpec extends FunSpec with Matchers {

  describe("#1 - Our exciting new Codemon datatype") {
    it("should always return itself with the identity morphism") {
      Rusa.identity shouldBe Rusa
      RaabyChu.identity shouldBe RaabyChu
    }

    it("should feature a basic evolution morphism") {
      Sikachu.evolve shouldBe RaabyChu
    }

    it("should not evolve past its final form") {
      RaabyChu.evolve shouldBe RaabyChu
    }

    it("should support multiple evolutions (associative law)") {
      Rusa.evolve.evolve shouldBe RaabyChu
    }
  }
}
