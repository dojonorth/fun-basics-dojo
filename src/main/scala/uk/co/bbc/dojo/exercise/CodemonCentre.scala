package uk.co.bbc.dojo.exercise

// More useful functor capabilities
case class CodemonCentre(codeBalls: CodeBall*) {
  def map(f: CodeBall => CodeBall): CodemonCentre = ??? //TODO: 4a-4d
}
