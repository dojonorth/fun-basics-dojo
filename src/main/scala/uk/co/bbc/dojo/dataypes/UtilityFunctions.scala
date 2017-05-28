package uk.co.bbc.dojo.dataypes

object UtilityFunctions {
  def evolveAll(codeBox: CodeBox[Codemon]): CodeBox[Codemon] = codeBox.map(Codemon.evolve)

  // Write map functionality using flatMap. Note how this highlights the power of flatMap, we can't define flatMap in terms of map.
  def evolveAllUsingFlatMap: (CodeBox[Codemon]) => CodeBox[CodeBox[Codemon]] = {
    def mapWrap[A, B](f: A => B): A => CodeBox[B] = (x: A) => CodeBox(f(x))

    mapWrap(evolveAll)
  }

  def keepSickachus(codemon: Codemon): CodeBox[Codemon] = codemon match {
    case Sikachu => CodeBox(Sikachu)
    case _ => CodeBox()
  }

  def replaceRaabyChusWithAThousandRusas(codemon: Codemon): CodeBox[Codemon] = codemon match {
    case RaabyChu => CodeBox(List.fill(1000)(Rusa))
    case notARusa => CodeBox(notARusa)
  }
}
