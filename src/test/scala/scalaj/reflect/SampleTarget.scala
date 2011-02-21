package scalaj.reflect


class SampleTarget(param1: String, param2: Int) {

  def this(param2a: Float) = this("float", param2a.toInt) 

  type StringAlias = String
  
  class Foo(val param: String)

//  case class Bar(val param: String)

  implicit val implicitString = "xxx"

  var simpleVar = 42

  def simpleMethod(arg: String) = "xxx"
  def defaultArgMethod(arg: String = "default") = "xxx"
  def implicitArgMethod(implicit arg: String) = "xxx"

  def multiArgMethod(arg1: String, arg2: String) = "xxx"
  def multiBlockMethod(arg1: String)(arg2: String) = "xxx"

  def genericMethod[T](arg: T) = "xxx"
  def viewBoundMethod[T <% Ordered[T]](arg: T) = "xxx"

}

//object SampleTarget
