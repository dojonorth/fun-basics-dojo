package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class AdvancedCodeballSpec extends CodemonBaseSpec {
  describe("#5 - The Advanced Codeball can apply functions to its contents, changing its type") {
    it("a. can turn water into wine") {
      object Water
      object Wine

      AdvancedCodeball.map(OccupiedCodeball(Water))(_ => Wine) shouldBe OccupiedCodeball(Wine)
    }

    it("b. can evolve a Codemon") {
      val codemon = Rusa
      val advancedCodeball = OccupiedCodeball(codemon)

      AdvancedCodeball.map(advancedCodeball)(Codemon.evolve) shouldBe OccupiedCodeball(Codemon.evolve(codemon))
    }

    it("c. can empty out a nested codeball") {
      val contents = "Something Magical"
      val innerBall = OccupiedCodeball(contents)
      val nestedBall = OccupiedCodeball(innerBall)

      def emptyingFunction[A](codeBall: Codeball[A]) = codeBall.get

      AdvancedCodeball.map(nestedBall)(emptyingFunction) shouldBe OccupiedCodeball(contents)
    }

    it("d. or it can safely do nothing if it's already empty") {
      AdvancedCodeball.map(EmptyCodeball)(_ => "you're never seeing this") shouldBe EmptyCodeball
    }
  }
}
