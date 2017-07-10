package uk.co.bbc.dojo.exercise

import uk.co.bbc.dojo.exercise.WildCodemonCapture.{baitTrap, fastEvolveCodemon, throwCodeball}

object ImprovedWildCodemonCapture {
  //TODO: Probably get rid of this and use a single ball type.
  import scala.language.implicitConversions
  implicit def advavancedCodeballToMasterCodeball[A](advancedCodeball: AdvancedCodeball[A]): MasterCodeball[A] = advancedCodeball match {
    case EmptyAdvancedBall => EmptyMasterBall
    case OccupiedAdvancedBall(contents) => OccupiedMasterBall(contents)
  }

  def mapViaFlatmap[A, B](masterCodeball: MasterCodeball[A])(f: A => B): MasterCodeball[B] =
    MasterCodeball.flatMap(masterCodeball)((x: A) => MasterCodeball.pure(f(x)))

  def captureLifecyclewithMonads(randomFunction: () => Boolean): MasterCodeball[Codemon] =
    MasterCodeball.flatMap(throwCodeball(randomFunction)) { codemonWeCaught: Codemon =>
      MasterCodeball.flatMap(baitTrap(codemonWeCaught)) { trappedNewCodemon: Codemon =>
        fastEvolveCodemon(randomFunction)(trappedNewCodemon)
      }
    }
}
