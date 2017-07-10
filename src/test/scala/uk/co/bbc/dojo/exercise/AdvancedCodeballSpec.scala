package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class AdvancedCodeballSpec extends CodemonBaseSpec {
  describe("#5 - The Advanced Codeball can apply functions to its contents, changing its type") {
    it("a. mapping over the identity function has no effect") {
      val occupiedCodeball = OccupiedCodeball(Rusa)
      AdvancedCodeball.map(occupiedCodeball)(x => x) shouldBe occupiedCodeball
    }

    it("b. can turn water into wine") {
      object Water
      object Wine

      AdvancedCodeball.map(OccupiedCodeball(Water))(_ => Wine) shouldBe OccupiedCodeball(Wine)
    }

    it("c. can evolve a Codemon") {
      val codemon = Rusa
      val advancedCodeball = OccupiedCodeball(codemon)

      AdvancedCodeball.map(advancedCodeball)(Codemon.evolve) shouldBe OccupiedCodeball(Codemon.evolve(codemon))
    }

    it("d. can empty out a nested codeball") {
      val contents = "Something Magical"
      val innerBall = OccupiedCodeball(contents)
      val nestedBall = OccupiedCodeball(innerBall)

      def emptyingFunction[A](codeBall: Codeball[A]) = codeBall.get

      AdvancedCodeball.map(nestedBall)(emptyingFunction) shouldBe OccupiedCodeball(contents)
    }

    it("e. or it can safely do nothing if it's already empty") {
      AdvancedCodeball.map(EmptyCodeball)(_ => "you're never seeing this") shouldBe EmptyCodeball
    }

    it("f. composing two functions then mapping voer them is the same as calling map on each in order") {
      def plusEleven(x: Int): Int = x + 11
      def divideByTwo(x: Int): Int = x / 2
      def composite(x: Int): Int = divideByTwo(plusEleven(x))

      val codeball = OccupiedCodeball(7)
      AdvancedCodeball.map(AdvancedCodeball.map(codeball)(plusEleven))(divideByTwo) shouldBe AdvancedCodeball.map(codeball)(composite)
    }
  }
}
