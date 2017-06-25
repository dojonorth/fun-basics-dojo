package uk.co.bbc.dojo.exercise

class MasterCodeBallSpec extends CodemonBaseSpec {
  describe("The Master Codeball can capture anything") {
    it("including a Codemon") {
      val aCodemon = RaabyChu
      OccupiedMasterBall(aCodemon).get shouldBe aCodemon
    }

    it("including a Codeball!") {
      val aCodeball = OccupiedCodeball(RaabyChu)
      OccupiedMasterBall(aCodeball).get shouldBe aCodeball
    }

    it("including the number 8!") {
      val theNumberEight = 8
      OccupiedMasterBall(8).get shouldBe theNumberEight
    }

    it("including another Master Ball!") {
      val theMoon = "The Moon!"
      val anotherMasterball = OccupiedMasterBall(theMoon)

      val theNestedMasterball = OccupiedMasterBall(anotherMasterball)

      theNestedMasterball.get shouldBe anotherMasterball
      theNestedMasterball.get.get shouldBe theMoon //Just to prove the point
    }

    it("...or it can be empty and throw an exception if we try and grab its contents") {
      intercept[RuntimeException] {
        EmptyMasterBall.get
      }
    }
  }

  describe("The Master Codeball can apply functions to its contents, changing its type") {
    it("can turn water into wine") {
      object Water
      object Wine

      MasterCodeBall.map(OccupiedMasterBall(Water))(_ => Wine) shouldBe OccupiedMasterBall(Wine)
    }

    it("can evolve a Codemon") {
      val codemon = Rusa
      val masterCodeball = OccupiedMasterBall(codemon)

      MasterCodeBall.map(masterCodeball)(Codemon.evolve) shouldBe OccupiedMasterBall(Codemon.evolve(codemon))
    }

    it("can empty out a nested codeball") {
      val contents = "Something Magical"
      val innerBall = OccupiedMasterBall(contents)
      val nestedBall = OccupiedMasterBall(innerBall)

      def emptyingFunction[A](codeBall: MasterCodeBall[A]) = codeBall.get

      MasterCodeBall.map(nestedBall)(emptyingFunction) shouldBe OccupiedMasterBall(contents)
    }

    it("or it can safely do nothing if it's already empty") {
      MasterCodeBall.map(EmptyMasterBall)(_ => "you're never seeing this") shouldBe EmptyMasterBall
    }
  }
}
