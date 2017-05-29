package uk.co.bbc.dojo.solution

// More useful functor capabilities
case class CodemonCentre(codeBalls: CodeBall*) {
  def map(f: CodeBall => CodeBall): CodemonCentre = CodemonCentre(codeBalls.map(codeball => f(codeball)): _*)
}
