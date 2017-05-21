package uk.co.bbc.dojo.datatypes

import uk.co.bbc.dojo.dataypes._

class CodeBoxSpec extends CodemonBaseSpec {
  describe("#5 - Our Codemon Brand box should") {
    it("be able to contain Codeballs") {
      val codeballs = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val codeBox = CodeBox(codeballs)

      codeBox.contents shouldBe codeballs
    }

    it("be able to contain Codemon") {
      val codemon = List(RaabyChu, Rusa)

      val codeBox = CodeBox(codemon)

      codeBox.contents shouldBe codemon
    }

    it("should be able to free Codemon from their Balls") {
      val codeballs = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val codeBox = CodeBox(codeballs)

      codeBox.map(_.chooseYou) shouldBe CodeBox(List(RaabyChu, Rusa))
    }
  }

  describe("#6 - The Exciting new flatten functionality that our CodeBox supports") {
    it("should empty out a single inner box of Codemon") {
      val codemon = List[Codemon](RaabyChu, Sikachu)

      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      CodeBox.flatten(codeBox) shouldBe CodeBox(codemon)
    }

    it("should empty out all inner boxes of Codemon") {
      val someCodemon = List(Sikachu, RaabyChu)
      val anotherCodemon = List[Codemon](Rusa)
      val aFinalFewCodemon = List(Rusa, Sikachu)

      val packedOuterCodebox = CodeBox(List(CodeBox(someCodemon), CodeBox(anotherCodemon), CodeBox(aFinalFewCodemon)))

      CodeBox.flatten(packedOuterCodebox) shouldBe CodeBox(someCodemon ++ anotherCodemon ++ aFinalFewCodemon)
    }
  }
}
