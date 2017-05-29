package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise._

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

      codeBox.map(_.chooseYou) shouldBe CodeBox(RaabyChu, Rusa)
    }

    it("should be able to facilitate mass Codemon evolution") {
      val codemon = List[Codemon](RaabyChu, Rusa)
      val codeBox = CodeBox(codemon)

      def evolveAll(codeBox: CodeBox[Codemon]): CodeBox[Codemon] = codeBox.map(Codemon.evolve)

      evolveAll(codeBox) shouldBe CodeBox(RaabyChu, Sikachu)
    }
  }

  describe("#6 - The Exciting new flatten functionality that our CodeBox supports") {
    it("should empty out a single inner box of Codemon") {
      val codemon = List[Codemon](RaabyChu, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      CodeBox.flatten(codeBox) shouldBe CodeBox(codemon)
    }

    it("should empty out all inner boxes of Codemon") {
      val someCodemon: List[Codemon] = List(Sikachu, RaabyChu)
      val anotherCodemon: List[Codemon] = List[Codemon](Rusa)
      val aFinalFewCodemon: List[Codemon] = List(Rusa, Sikachu)

      val packedOuterCodebox = CodeBox[CodeBox[Codemon]](new CodeBox(someCodemon), new CodeBox(anotherCodemon), new CodeBox(aFinalFewCodemon))

      CodeBox.flatten(packedOuterCodebox) shouldBe CodeBox(someCodemon ++ anotherCodemon ++ aFinalFewCodemon)
    }
  }

  describe("#7 - The killer app, flatmap, should") {
    it("act as flaten if we pass the identity function") {
      val codemon = List[Codemon](RaabyChu, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      codeBox.flatMap(CodeBox.identity) shouldBe CodeBox.flatten(codeBox)
    }

    it("act as map if we wrap the function in the unit function") {
      val codemon = List[Codemon](RaabyChu, Rusa, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      codeBox.flatMap(UtilityFunctions.evolveAllUsingFlatMap) shouldBe codeBox.map(UtilityFunctions.evolveAll)
    }

    it("allow us to throw away all but the Sikachus") {
      val codemon = List[Codemon](Sikachu, Sikachu, RaabyChu, Rusa, Sikachu)
      val codeBox: CodeBox[Codemon] = CodeBox(codemon)

      codeBox.flatMap(UtilityFunctions.keepSickachus) shouldBe CodeBox(Sikachu, Sikachu, Sikachu)
    }

    it("Allow us replace each RaabyChu with the equivalant Thousand Rusas") {
      val codeBox: CodeBox[Codemon] = CodeBox(RaabyChu, Rusa, Sikachu, RaabyChu, Rusa)

      val aThousandRusas = List.fill(1000)(Rusa)
      val expectedList = aThousandRusas ++ List(Rusa) ++ List(Sikachu) ++ aThousandRusas ++ List(Rusa)

      codeBox.flatMap(UtilityFunctions.replaceRaabyChusWithAThousandRusas) shouldBe CodeBox(expectedList)
    }
  }
}
