package scalaj.reflect.targets

class TweedleDee {
  class Inner {
    val member = "XXX"
  }
  def getInner = new Inner
}
