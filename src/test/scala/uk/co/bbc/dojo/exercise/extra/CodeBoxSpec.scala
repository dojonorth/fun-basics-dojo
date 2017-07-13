package uk.co.bbc.dojo.exercise.extra

import uk.co.bbc.dojo.exercise._
import uk.co.bbc.dojo.exercise.housekeeping.CodemonBaseSpec

class CodeBoxSpec extends CodemonBaseSpec {
  describe("#14 - Our Codemon Brand box should") {
    ignore("a. be able to contain Codeballs") {
      val codeballs = List(OccupiedBeginnersCodeball(RaabyChu), OccupiedBeginnersCodeball(Rusa))

      val codeBox = CodeBox(codeballs)

      codeBox.contents shouldBe codeballs
    }

    ignore("b. be able to contain Codemon") {
      val codemon = List(RaabyChu, Rusa)

      val codeBox = CodeBox(codemon)

      codeBox.contents shouldBe codemon
    }

    ignore("c. should be able to map across its contents and change its type") {
      val codeballs = List(OccupiedBeginnersCodeball(RaabyChu), OccupiedBeginnersCodeball(Rusa))

      val codeBox = CodeBox(codeballs)

      codeBox.map(_.codemon) shouldBe CodeBox(RaabyChu, Rusa)
    }

    ignore("d. should be able to facilitate mass Codemon evolution") {
      val codemon = List[Codemon](RaabyChu, Rusa)
      val codeBox = CodeBox(codemon)

      def evolveAll(codeBox: CodeBox[Codemon]): CodeBox[Codemon] = codeBox.map(Codemon.evolve)

      evolveAll(codeBox) shouldBe CodeBox(RaabyChu, Sikachu)
    }
  }

  describe("#15 - The Exciting new flatten functionality that our CodeBox supports should") {
    ignore("a. empty out a single inner box of Codemon") {
      val codemon = List[Codemon](RaabyChu, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      CodeBox.flatten(codeBox) shouldBe CodeBox(codemon)
    }

    ignore("b. empty out all inner boxes of Codemon") {
      val someCodemon: List[Codemon] = List(Sikachu, RaabyChu)
      val anotherCodemon: List[Codemon] = List[Codemon](Rusa)
      val aFinalFewCodemon: List[Codemon] = List(Rusa, Sikachu)

      val packedOuterCodebox = CodeBox[CodeBox[Codemon]](new CodeBox(someCodemon), new CodeBox(anotherCodemon), new CodeBox(aFinalFewCodemon))

      CodeBox.flatten(packedOuterCodebox) shouldBe CodeBox(someCodemon ++ anotherCodemon ++ aFinalFewCodemon)
    }
  }

  describe("#16 - The killer app, flatmap, should") {
    ignore("a. act as flaten if we pass the identity function") {
      val codemon = List[Codemon](RaabyChu, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      codeBox.flatMap(CodeBox.identity) shouldBe CodeBox.flatten(codeBox)
    }

    ignore("b. act as map if we wrap the function in the unit function") {
      val codemon = List[Codemon](RaabyChu, Rusa, Sikachu)
      val codeBox: CodeBox[CodeBox[Codemon]] = CodeBox(List(CodeBox(codemon)))

      codeBox.flatMap(UtilityFunctions.evolveAllUsingFlatMap) shouldBe codeBox.map(UtilityFunctions.evolveAll)
    }

    ignore("c. allow us to throw away all but the Sikachus") {
      val codemon = List[Codemon](Sikachu, Sikachu, RaabyChu, Rusa, Sikachu)
      val codeBox: CodeBox[Codemon] = CodeBox(codemon)

      codeBox.flatMap(UtilityFunctions.keepSickachus) shouldBe CodeBox(Sikachu, Sikachu, Sikachu)
    }

    ignore("d. allow us replace each RaabyChu with the equivalant Thousand Rusas") {
      val codeBox: CodeBox[Codemon] = CodeBox(RaabyChu, Rusa, Sikachu, RaabyChu, Rusa)

      val aThousandRusas = List.fill(1000)(Rusa)
      val expectedList = aThousandRusas ++ List(Rusa) ++ List(Sikachu) ++ aThousandRusas ++ List(Rusa)

      codeBox.flatMap(UtilityFunctions.replaceRaabyChusWithAThousandRusas) shouldBe CodeBox(expectedList)
    }
  }
}
