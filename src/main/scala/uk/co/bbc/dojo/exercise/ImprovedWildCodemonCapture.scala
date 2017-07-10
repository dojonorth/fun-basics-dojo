package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.WildCodemonCapture.{baitTrap, fastEvolveCodemon, throwCodeball}

object ImprovedWildCodemonCapture {
  def mapViaFlatmap[A, B](masterCodeball: Codeball[A])(f: A => B): Codeball[B] =
    MasterCodeball.flatMap(masterCodeball)((x: A) => MasterCodeball.pure(f(x)))

  def captureLifecyclewithMonads(randomFunction: () => Boolean): Codeball[Codemon] =
    MasterCodeball.flatMap(throwCodeball(randomFunction)) { codemonWeCaught: Codemon =>
      MasterCodeball.flatMap(baitTrap(codemonWeCaught)) { trappedNewCodemon: Codemon =>
        fastEvolveCodemon(randomFunction)(trappedNewCodemon)
      }
    }
}
