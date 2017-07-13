package uk.co.bbc.dojo.exercise

object WildCodemonCapture {
  // Tests assume that we'll either capture a Rusa or nothing
  def throwCodeball(randomFunction: () => Boolean): Codeball[Codemon] = ???

  // If the bait is a Rusa, it will always capture a Sikachu. Otherwise we'll be left with nothing.
  def baitTrap(bait: Codemon): Codeball[Codemon] = ???

  // Try for a fast evolution. If we're lucky and the random function comes in, then we'll evolve the Codemon. Otherwise it all goes wrong and we end up with nothing.
  def fastEvolveCodemon(randomFunction: () => Boolean)(codemonToEvolve: Codemon): Codeball[Codemon] = ???

  // Time to make a quick buck! Combine the other operations in order and return the result.
  def captureLifecycle(randomFunction: () => Boolean): Codeball[Codeball[Codeball[Codemon]]] = ???
}
