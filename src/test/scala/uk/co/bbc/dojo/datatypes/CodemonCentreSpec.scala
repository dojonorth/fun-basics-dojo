package uk.co.bbc.dojo.datatypes

import uk.co.bbc.dojo.dataypes._

class CodemonCentreSpec extends CodemonBaseSpec {
  describe("#4 - The dystopian mass processing facility should") {
    it("be able to contain no Codeballs") {
      val centre = CodemonCentre()

      centre.codeBalls shouldBe Seq.empty[CodeBall]
    }

    it("do nothing when we apply a functor over an empty facility") {
      val centre = CodemonCentre()

      val evolvedCentre = centre.map(codeBall => codeBall.map(Codemon.evolve))

      evolvedCentre shouldBe centre
    }

    it("be able to contain and retrieve a number of Codemon") {
      val convicts = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val centre = CodemonCentre(convicts: _*)

      centre.codeBalls shouldBe convicts
    }

    it("be able to mass-process the imprisoned Codemon") {
      val convicts = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val centre = CodemonCentre(convicts: _*)
      val evolvedCentre = centre.map(codeBall => codeBall.map(Codemon.evolve))

      evolvedCentre.codeBalls shouldBe List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Sikachu))
    }
  }
}
