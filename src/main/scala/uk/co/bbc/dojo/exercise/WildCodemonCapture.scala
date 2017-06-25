package uk.co.bbc.dojo.exercise

object WildCodemonCapture {
  def throwCodeball(randomFunction: () => Boolean): AdvancedCodeball[Codemon] =
    if (randomFunction()) OccupiedAdvancedBall(Rusa) else EmptyAdvancedBall

  def baitTrap(bait: Codemon): AdvancedCodeball[Codemon] = bait match {
    case Rusa => OccupiedAdvancedBall(Sikachu)
    case _ => EmptyAdvancedBall
  }

  def fastEvolveCodemon(randomFunction: () => Boolean)(codemonToEvolve: Codemon): AdvancedCodeball[Codemon] =
    if (randomFunction()) OccupiedAdvancedBall(Codemon.evolve(codemonToEvolve)) else EmptyAdvancedBall

  //TODO: Note the clunky type
  def captureLifecycle(randomFunction: () => Boolean): AdvancedCodeball[AdvancedCodeball[AdvancedCodeball[Codemon]]] =
    AdvancedCodeball.map(throwCodeball(randomFunction)) { codemonWeCaught: Codemon =>
      AdvancedCodeball.map(baitTrap(codemonWeCaught)) { trappedNewCodemon: Codemon =>
        fastEvolveCodemon(randomFunction)(trappedNewCodemon)
      }
    }
}
