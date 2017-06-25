package uk.co.bbc.dojo.exercise

object WildCodemonCapture {
  def throwCodeball(randomFunction: () => Boolean): MasterCodeBall[Codemon] =
    if (randomFunction()) OccupiedMasterBall(Rusa) else EmptyMasterBall

  def baitTrap(bait: Codemon): MasterCodeBall[Codemon] = bait match {
    case Rusa => OccupiedMasterBall(Sikachu)
    case _ => EmptyMasterBall
  }

  def fastEvolveCodemon(randomFunction: () => Boolean)(codemonToEvolve: Codemon): MasterCodeBall[Codemon] =
    if (randomFunction()) OccupiedMasterBall(Codemon.evolve(codemonToEvolve)) else EmptyMasterBall

  //TODO: Note the clunky type
  def captureLifecycle(randomFunction: () => Boolean): MasterCodeBall[MasterCodeBall[MasterCodeBall[Codemon]]] =
    MasterCodeBall.map(throwCodeball(randomFunction)) { codemonWeCaught: Codemon =>
      MasterCodeBall.map(baitTrap(codemonWeCaught)) { trappedNewCodemon: Codemon =>
        fastEvolveCodemon(randomFunction)(trappedNewCodemon)
      }
    }
}
