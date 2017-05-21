package uk.co.bbc.dojo.dataypes

// More useful functor capabilities
case class CodemonCentre(codeBalls: CodeBall*) {
  def map(f: CodeBall => CodeBall): CodemonCentre = CodemonCentre(codeBalls.map(codeball => f(codeball)): _*)
}
