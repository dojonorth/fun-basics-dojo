package uk.co.bbc.dojo.dataypes

object CodeBox {
  def identity[A](codeBox: CodeBox[A]): CodeBox[A] = codeBox

  def flatten[A](codebox: CodeBox[CodeBox[A]]): CodeBox[A] = {
    val newContents = go(codebox.contents)
    CodeBox(newContents)
  }

  private def go[A](toFlatten: List[CodeBox[A]]): List[A] = toFlatten match {
    case Nil => Nil
    case (head: CodeBox[A]) :: tail => head.contents ++ go(tail)
  }
}

// Now genericised the functor to allow changes between types
case class CodeBox[A](contents: List[A]) {
  def map[B](f: A => B): CodeBox[B] = CodeBox[B](contents.map(f))
  def flatMap[B](f: A => CodeBox[B]): CodeBox[B] = CodeBox.flatten(map(f))
}
