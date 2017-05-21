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

    it("should be able to facilitate mass Codemon evolution") {
      val codemon = List(RaabyChu, Rusa)
      val codeBox = CodeBox(codemon)

      def evolveAll(codeBox: CodeBox[Codemon]): CodeBox[Codemon] = codeBox.map(Codemon.evolve)

      evolveAll(codeBox) shouldBe CodeBox(List(RaabyChu, Sikachu))
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

  //TODO: Restriction on Flatmap?
  //TODO: Make writing these tests the exercise
  describe("#7 - The killer app, flatmap, should") {
    it("act as flaten if we pass the identity function") {
      val codemon = List[Codemon](RaabyChu, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      codeBox.flatMap(CodeBox.identity) shouldBe CodeBox.flatten(codeBox)
    }

    it("act as map if we wrap the function in the unit function") {
      val codemon = List[Codemon](RaabyChu, Rusa, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      def mapWrap[A,B](f: A => B): A => CodeBox[B] = (x: A) => CodeBox(List(f(x)))
      def evolveAll(codeBox: CodeBox[Codemon]): CodeBox[Codemon] = codeBox.map(Codemon.evolve)
      def combinedFunction: (CodeBox[Codemon]) => CodeBox[CodeBox[Codemon]] = mapWrap(evolveAll)

      codeBox.flatMap(combinedFunction) shouldBe codeBox.map(evolveAll)
    }

    it("allow us to throw away all but the Sikachus") {
      val codemon = List[Codemon](Sikachu, Sikachu, RaabyChu, Rusa, Sikachu)
      val codeBox: CodeBox[Codemon] = CodeBox(codemon)

      def keepSickachus(codemon: Codemon): CodeBox[Codemon] = codemon match {
        case Sikachu => CodeBox(List(Sikachu))
        case _ => CodeBox(List())
      }

      codeBox.flatMap(keepSickachus) shouldBe CodeBox(List[Codemon](Sikachu, Sikachu, Sikachu))
    }

    it("Allow us replace each RaabyChu with the equivalant Thousand Rusas") {
      val codemon = List[Codemon](RaabyChu, Rusa, Sikachu, RaabyChu, Rusa)
      val codeBox: CodeBox[Codemon] = CodeBox(codemon)

      val aThousandRusas = List.fill(1000)(Rusa)
      def keepSickachus(codemon: Codemon): CodeBox[Codemon] = codemon match {
        case RaabyChu => CodeBox(aThousandRusas)
        case notARusa => CodeBox(List(notARusa))
      }

      val expectedList = aThousandRusas ++ List(Rusa) ++ List(Sikachu) ++ aThousandRusas ++ List(Rusa)

      codeBox.flatMap(keepSickachus) shouldBe CodeBox(expectedList)
    }
  }
}
