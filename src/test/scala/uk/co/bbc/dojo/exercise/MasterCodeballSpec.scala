package uk.co.bbc.dojo.exercise

class MasterCodeballSpec extends CodemonBaseSpec {
  describe("#4 - The Master Codeball can capture anything") {
    it("a. including a Codemon") {
      val aCodemon = RaabyChu
      OccupiedMasterBall(aCodemon).get shouldBe aCodemon
    }

    it("b. including a Codeball!") {
      val aCodeball = OccupiedCodeball(RaabyChu)
      OccupiedMasterBall(aCodeball).get shouldBe aCodeball
    }

    it("c. including the number 8!") {
      val theNumberEight = 8
      OccupiedMasterBall(8).get shouldBe theNumberEight
    }

    it("d. including another Master Ball!") {
      val theMoon = "The Moon!"
      val anotherMasterball = OccupiedMasterBall(theMoon)

      val theNestedMasterball = OccupiedMasterBall(anotherMasterball)

      theNestedMasterball.get shouldBe anotherMasterball
      theNestedMasterball.get.get shouldBe theMoon //Just to prove the point
    }

    it("e. ...or it can be empty and throw an exception if we try and grab its contents") {
      intercept[RuntimeException] {
        EmptyMasterBall.get
      }
    }
  }

  describe("#5 - The Master Codeball can apply functions to its contents, changing its type") {
    it("a. can turn water into wine") {
      object Water
      object Wine

      MasterCodeball.map(OccupiedMasterBall(Water))(_ => Wine) shouldBe OccupiedMasterBall(Wine)
    }

    it("b. can evolve a Codemon") {
      val codemon = Rusa
      val masterCodeball = OccupiedMasterBall(codemon)

      MasterCodeball.map(masterCodeball)(Codemon.evolve) shouldBe OccupiedMasterBall(Codemon.evolve(codemon))
    }

    it("c. can empty out a nested codeball") {
      val contents = "Something Magical"
      val innerBall = OccupiedMasterBall(contents)
      val nestedBall = OccupiedMasterBall(innerBall)

      def emptyingFunction[A](codeBall: MasterCodeball[A]) = codeBall.get

      MasterCodeball.map(nestedBall)(emptyingFunction) shouldBe OccupiedMasterBall(contents)
    }

    it("d. or it can safely do nothing if it's already empty") {
      MasterCodeball.map(EmptyMasterBall)(_ => "you're never seeing this") shouldBe EmptyMasterBall
    }
  }
}
