package uk.co.bbc.dojo.exercise

class CodemonCentreSpec extends CodemonBaseSpec {
  describe("#4 - The dystopian mass processing facility should") {
    it("a. be able to contain no Codeballs") {
      val centre = CodemonCentre()

      centre.codeBalls shouldBe Seq.empty[CodeBall]
    }

    it("b. do nothing when we apply a functor over an empty facility") {
      val centre = CodemonCentre()

      val evolvedCentre = centre.map(codeBall => codeBall.map(Codemon.evolve))

      evolvedCentre shouldBe centre
    }

    it("c. be able to contain and retrieve a number of Codemon") {
      val convicts = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val centre = CodemonCentre(convicts: _*)

      centre.codeBalls shouldBe convicts
    }

    it("d. be able to mass-process the imprisoned Codemon") {
      val convicts = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val centre = CodemonCentre(convicts: _*)
      val evolvedCentre = centre.map(codeBall => codeBall.map(Codemon.evolve))

      evolvedCentre.codeBalls shouldBe List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Sikachu))
    }
  }
}
