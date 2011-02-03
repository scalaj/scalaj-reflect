package scalaj.reflect


class ForwardPipe[A](value: A) {
  def |>[B](f: A => B): B = f(value)
}

object ForwardPipe {
  implicit def apply[A](a: => A) = new ForwardPipe(a)
}
