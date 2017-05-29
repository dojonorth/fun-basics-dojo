package uk.co.bbc.dojo.exercise

class CodemonCentreSpec extends CodemonBaseSpec {
  describe("#4 - The dystopian mass processing facility should") {
    ignore("a. be able to contain no Codeballs") {
      val centre = CodemonCentre()

      centre.codeBalls shouldBe Seq.empty[CodeBall]
    }

    ignore("b. do nothing when we apply a functor over an empty facility") {
      val centre = CodemonCentre()

      val evolvedCentre = centre.map(codeBall => codeBall.map(Codemon.evolve))

      evolvedCentre shouldBe centre
    }

    ignore("c. be able to contain and retrieve a number of Codemon") {
      val convicts = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val centre = CodemonCentre(convicts: _*)

      centre.codeBalls shouldBe convicts
    }

    ignore("d. be able to mass-process the imprisoned Codemon") {
      val convicts = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val centre = CodemonCentre(convicts: _*)
      val evolvedCentre = centre.map(codeBall => codeBall.map(Codemon.evolve))

      evolvedCentre.codeBalls shouldBe List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Sikachu))
    }
  }
}
