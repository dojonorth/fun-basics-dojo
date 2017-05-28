package uk.co.bbc.dojo.dataypes

import scala.util.Random

class CodemonWorld(private val random: Random = new Random) {
  def tryAndCapture(numberOfThrows: Int): CodeBox[CodeBall] = CodeBox(List.fill(numberOfThrows)(throwCodeball()))

  private def throwCodeball(): CodeBall =
    random.nextInt(100) match {
      case x if x < 10 => OccupiedCodeball(Rusa) //10% chance
      case x if x < 30 => OccupiedCodeball(Sikachu) //20% chance
      case x if x == 99 => OccupiedCodeball(RaabyChu) //Super rare!
      case _ => EmptyCodeball
    }

  def forceMaxEvolution(codemon: Codemon): CodeBall = {
    def go(codeBall: CodeBall): CodeBall = codeBall match {
      case EmptyCodeball => EmptyCodeball
      case codeBall@OccupiedCodeball(capturedCodemon) => if (finalForm(capturedCodemon)) codeBall else go(riskyForcedEvolution(codeBall))
    }

    def riskyForcedEvolution(occupiedCodeball: OccupiedCodeball): CodeBall =
      random.nextInt(100) match {
        case x if x < 75 => EmptyCodeball
        case _ => occupiedCodeball.map(Codemon.evolve)
      }

    go(OccupiedCodeball(codemon))
  }

  private def finalForm(codemon: Codemon): Boolean = Codemon.evolve(codemon) == codemon

  def codemonIndustry(numberOfThrows: Int): CodeBox[OccupiedCodeball] = {
    val codeBallHaul: CodeBox[CodeBall] = tryAndCapture(numberOfThrows)
    val capturedCodemon: CodeBox[Codemon] = codeBallHaul.flatMap(emptyOutCodeBalls)
    val raabychusAndCorpses: CodeBox[CodeBall] = capturedCodemon.flatMap(codemon => CodeBox(List(forceMaxEvolution(codemon)))) //Could use map, but want to show repeated flatmaps
    val onlyRaabychus: CodeBox[Codemon] = raabychusAndCorpses.flatMap(emptyOutCodeBalls)
    onlyRaabychus.map(raabyChu => OccupiedCodeball(raabyChu))
  }

  private def emptyOutCodeBalls(codeBall: CodeBall): CodeBox[Codemon] = codeBall match {
    case OccupiedCodeball(codemon) => CodeBox(List(codemon))
    case EmptyCodeball => CodeBox(List())
  }
}
