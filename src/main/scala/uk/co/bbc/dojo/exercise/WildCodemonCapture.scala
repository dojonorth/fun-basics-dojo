package uk.co.bbc.dojo.exercise

object WildCodemonCapture {
  def throwCodeball(randomFunction: () => Boolean): Codeball[Codemon] =
    if (randomFunction()) OccupiedCodeball(Rusa) else EmptyCodeball

  def baitTrap(bait: Codemon): Codeball[Codemon] = bait match {
    case Rusa => OccupiedCodeball(Sikachu)
    case _ => EmptyCodeball
  }

  def fastEvolveCodemon(randomFunction: () => Boolean)(codemonToEvolve: Codemon): Codeball[Codemon] =
    if (randomFunction()) OccupiedCodeball(Codemon.evolve(codemonToEvolve)) else EmptyCodeball

  def captureLifecycle(randomFunction: () => Boolean): Codeball[Codeball[Codeball[Codemon]]] =
    AdvancedCodeball.map(throwCodeball(randomFunction)) { codemonWeCaught: Codemon =>
      AdvancedCodeball.map(baitTrap(codemonWeCaught)) { trappedNewCodemon: Codemon =>
        fastEvolveCodemon(randomFunction)(trappedNewCodemon)
      }
    }
}
