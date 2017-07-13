package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.WildCodemonCapture.{baitTrap, fastEvolveCodemon, throwCodeball}

object ImprovedWildCodemonCapture {
  def mapViaFlatmap[A, B](masterCodeball: Codeball[A])(f: A => B): Codeball[B] = ???

  def captureLifecyclewithMonads(randomFunction: () => Boolean): Codeball[Codemon] = ???

  def captureLifecycleUsingAForComprehension(randomFunction: () => Boolean): Codeball[Codemon] = ???
}
