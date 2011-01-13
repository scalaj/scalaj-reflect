package scalaj.reflect


class SampleTarget {
  class Foo(val param: String)

//  case class Bar(val param: String)

  implicit val implicitString = "xxx"

  def simpleMethod(arg: String) = "xxx"
  def defaultArgMethod(arg: String = "default") = "xxx"
  def implicitArgMethod(implicit arg: String) = "xxx"

  def multiArgMethod(arg1: String, arg2: String) = "xxx"
  def multiBlockMethod(arg1: String)(arg2: String) = "xxx"

  def genericMethod[T](arg: T) = "xxx"
  def viewBoundMethod[T <% Ordered[T]](arg: T) = "xxx"

}

//object SampleTarget
