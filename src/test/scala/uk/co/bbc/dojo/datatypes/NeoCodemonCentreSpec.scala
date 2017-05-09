package uk.co.bbc.dojo.datatypes

import uk.co.bbc.dojo.dataypes._

class NeoCodemonCentreSpec extends CodemonBaseSpec {
  describe("#5 - The upgraded dystopian mass processing facility should") {
    it("be able to contain Codeballs") {
      val codeballs = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val neoCentre = NeoCodemonCentre(codeballs: _*)

      neoCentre.contents shouldBe codeballs
    }

    it("be able to contain Codemon") {
      val codemon = List(RaabyChu, Rusa)

      val neoCentre = NeoCodemonCentre(codemon: _*)

      neoCentre.contents shouldBe codemon
    }

    it("should be able to free Codemon from their Balls") {
      val codeballs = List(OccupiedCodeball(RaabyChu), OccupiedCodeball(Rusa))

      val neoCentre = NeoCodemonCentre(codeballs: _*)

      neoCentre.map(_.chooseYou) shouldBe NeoCodemonCentre(RaabyChu, Rusa)
    }
  }
}
