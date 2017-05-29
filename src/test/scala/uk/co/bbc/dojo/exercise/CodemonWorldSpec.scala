package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise._

import scala.util.Random

class CodemonWorldSpec extends CodemonSpec {

  describe("#8 - The Codemon World should") {

    it("Allow us to try and capture wild Codemon") {
      val knownRandomGenerator = new Random(560562578l)
      val codemonWorld = new CodemonWorld(knownRandomGenerator)

      val todaysHaul: CodeBox[CodeBall] = codemonWorld.tryAndCapture(1000)

      countCodemonByType(todaysHaul) shouldBe Map(EmptyCodeball -> 705, OccupiedCodeball(Rusa) -> 87, OccupiedCodeball(Sikachu) -> 196, OccupiedCodeball(RaabyChu) -> 12)
    }

    it("allow for dangerous forced double evolution on Codemon") {
      val knownRandomGenerator = new Random(560562578l)
      val codemonWorld = new CodemonWorld(knownRandomGenerator)

      val someCodemon: CodeBox[Codemon] = CodeBox(List.fill(500)(Rusa) ++ List.fill(500)(Sikachu))
      val evolvedCodemon: CodeBox[CodeBall] = someCodemon.map(codemonWorld.forceMaxEvolution)

      countCodemonByType(evolvedCodemon) shouldBe Map(EmptyCodeball -> 826, OccupiedCodeball(RaabyChu) -> 174)
    }

    it("Allow us to efficiently bulk process Codemon") {
      val knownRandomGenerator = new Random(560562578l)
      val codemonWorld = new CodemonWorld(knownRandomGenerator)

      val todaysHaul = codemonWorld.codemonIndustry(1000)

      countCodemonByType(todaysHaul) shouldBe Map(OccupiedCodeball(RaabyChu) -> 65)
    }
  }

  private def countCodemonByType[A](codeBox: CodeBox[A]): Map[A, Int] = codeBox.contents.groupBy(x => x).mapValues(_.size)
}
