package uk.co.bbc.dojo.exercise

import scala.util.Random

class CodemonWorld(private val statefulRandomNumberGenerator: Random = new Random) { //Random gene isn't very functional, but simplifies the code.
  def tryAndCapture(numberOfThrows: Int): CodeBox[CodeBall] = CodeBox(List.fill(numberOfThrows)(throwCodeball()))

  private def throwCodeball(): CodeBall = ??? // TODO: 8a.
      //TODO: (Rusa) - 10% chance
      //TODO: Sikachu - 20% chance
      //TODO: RaabyChu - 1% chance - super rare!
      //TODO: EmptyCodeball - 69% chance

  def forceMaxEvolution(codemon: Codemon): CodeBall = ??? // TODO: 8b.

  def codemonIndustry(numberOfThrows: Int): CodeBox[OccupiedCodeball] = ??? //TODO: 8c.
}
