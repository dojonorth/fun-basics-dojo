package uk.co.bbc.dojo.exercise.extra

object CodeBox {
  def identity[A](codeBox: CodeBox[A]): CodeBox[A] = ???

  def flatten[A](codebox: CodeBox[CodeBox[A]]): CodeBox[A] = ???

  def apply[A](boxContents: A*): CodeBox[A] = ???
}

case class CodeBox[A](contents: List[A]) {
  def map[B](f: A => B): CodeBox[B] = ???
  def flatMap[B](f: A => CodeBox[B]): CodeBox[B] = ???
}
