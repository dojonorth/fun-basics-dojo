package uk.co.bbc.dojo.exercise

object CodeBox {
  def identity[A](codeBox: CodeBox[A]): CodeBox[A] = ???

  def flatten[A](codebox: CodeBox[CodeBox[A]]): CodeBox[A] = ??? //TODO: 6a - 6b

  def apply[A](boxContents: A*): CodeBox[A] = CodeBox(boxContents.toList)
}

// Now genericised the functor to allow changes between types
case class CodeBox[A](contents: List[A]) {
  def map[B](f: A => B): CodeBox[B] = ??? //TODO: 5a - 5d
  def flatMap[B](f: A => CodeBox[B]): CodeBox[B] = ??? //TODO: 7a - 7d
}
