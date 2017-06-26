package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class AdvancedCodeballSpec extends CodemonBaseSpec {
  describe("#4 - The Advanced Codeball can capture anything") {
    it("a. including a Codemon") {
      val aCodemon = RaabyChu
      OccupiedAdvancedBall(aCodemon).get shouldBe aCodemon
    }

    it("b. including a Codeball!") {
      val aCodeball = OccupiedCodeball(RaabyChu)
      OccupiedAdvancedBall(aCodeball).get shouldBe aCodeball
    }

    it("c. including the number 8!") {
      val theNumberEight = 8
      OccupiedAdvancedBall(8).get shouldBe theNumberEight
    }

    it("d. including another Advanced Ball!") {
      val theMoon = "The Moon!"
      val anotherAdvancedball = OccupiedAdvancedBall(theMoon)

      val theNestedAdvancedball = OccupiedAdvancedBall(anotherAdvancedball)

      theNestedAdvancedball.get shouldBe anotherAdvancedball
      theNestedAdvancedball.get.get shouldBe theMoon //Just to prove the point
    }

    it("e. ...or it can be empty and throw an exception if we try and grab its contents") {
      intercept[RuntimeException] {
        EmptyAdvancedBall.get
      }
    }
  }

  describe("#5 - The Advanced Codeball can apply functions to its contents, changing its type") {
    it("a. can turn water into wine") {
      object Water
      object Wine

      AdvancedCodeball.map(OccupiedAdvancedBall(Water))(_ => Wine) shouldBe OccupiedAdvancedBall(Wine)
    }

    it("b. can evolve a Codemon") {
      val codemon = Rusa
      val advancedCodeball = OccupiedAdvancedBall(codemon)

      AdvancedCodeball.map(advancedCodeball)(Codemon.evolve) shouldBe OccupiedAdvancedBall(Codemon.evolve(codemon))
    }

    it("c. can empty out a nested codeball") {
      val contents = "Something Magical"
      val innerBall = OccupiedAdvancedBall(contents)
      val nestedBall = OccupiedAdvancedBall(innerBall)

      def emptyingFunction[A](codeBall: AdvancedCodeball[A]) = codeBall.get

      AdvancedCodeball.map(nestedBall)(emptyingFunction) shouldBe OccupiedAdvancedBall(contents)
    }

    it("d. or it can safely do nothing if it's already empty") {
      AdvancedCodeball.map(EmptyAdvancedBall)(_ => "you're never seeing this") shouldBe EmptyAdvancedBall
    }
  }
}
