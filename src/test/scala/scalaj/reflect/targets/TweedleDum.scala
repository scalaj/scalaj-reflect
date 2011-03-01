package scalaj.reflect.targets;
class TweedleDum {
  class InnerDum {
    val member = "XXX"
  }

  type InnerT = InnerDum
  def getInner: InnerT = new InnerDum
}
