package uk.co.bbc.dojo.exercise

object WildCodemonCapture {
  def throwCodeball(randomFunction: () => Boolean): MasterCodeball[Codemon] =
    if (randomFunction()) OccupiedMasterBall(Rusa) else EmptyMasterBall

  def baitTrap(bait: Codemon): MasterCodeball[Codemon] = bait match {
    case Rusa => OccupiedMasterBall(Sikachu)
    case _ => EmptyMasterBall
  }

  def fastEvolveCodemon(randomFunction: () => Boolean)(codemonToEvolve: Codemon): MasterCodeball[Codemon] =
    if (randomFunction()) OccupiedMasterBall(Codemon.evolve(codemonToEvolve)) else EmptyMasterBall

  //TODO: Note the clunky type
  def captureLifecycle(randomFunction: () => Boolean): MasterCodeball[MasterCodeball[MasterCodeball[Codemon]]] =
    MasterCodeball.map(throwCodeball(randomFunction)) { codemonWeCaught: Codemon =>
      MasterCodeball.map(baitTrap(codemonWeCaught)) { trappedNewCodemon: Codemon =>
        fastEvolveCodemon(randomFunction)(trappedNewCodemon)
      }
    }
}
