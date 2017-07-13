package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class CodeballSpec extends CodemonBaseSpec {

  describe("#4 - The Codeball can capture anything") {
    it("a. including a Codemon") {
      val aCodemon = RaabyChu
      OccupiedCodeball(aCodemon).get shouldBe aCodemon
    }

    it("b. including a Codeball!") {
      val aCodeball = OccupiedBeginnersCodeball(RaabyChu)
      OccupiedCodeball(aCodeball).get shouldBe aCodeball
    }

    it("c. including the number 8!") {
      val theNumberEight = 8
      OccupiedCodeball(8).get shouldBe theNumberEight
    }

    it("d. including another Advanced Ball!") {
      val theMoon = "The Moon!"
      val anotherAdvancedball = OccupiedCodeball(theMoon)

      val theNestedAdvancedball = OccupiedCodeball(anotherAdvancedball)

      theNestedAdvancedball.get shouldBe anotherAdvancedball
      theNestedAdvancedball.get.get shouldBe theMoon //Just to prove the point
    }

    it("e. ...or it can be empty and throw an exception if we try and grab its contents") {
      intercept[RuntimeException] {
        EmptyCodeball.get
      }
    }
  }
}
