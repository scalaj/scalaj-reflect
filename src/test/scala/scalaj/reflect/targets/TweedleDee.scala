package scalaj.reflect.targets

class TweedleDee {
  class InnerDee {
    val member = (new TweedleDum).getInner
  }

  type InnerT = InnerDee
  def getInner: InnerT = new InnerDee
}
