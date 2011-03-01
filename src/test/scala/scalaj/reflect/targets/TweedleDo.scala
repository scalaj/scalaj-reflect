package scalaj.reflect.targets
class TweedleDo {

  def a = (new TweedleDee)
  val b: TweedleDee#InnerT = a.getInner
  val c = b.member
  def d = c.member

}
