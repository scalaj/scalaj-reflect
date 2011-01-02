package scalaj.reflect

import tools.scalap.scalax.rules.scalasig._

object Mirror {
  private def refinementClass(c: ClassSymbol) = c.name == "<refinement>"
  
  def of(sym: Symbol): Option[Mirror] = sym match {
    case o: ObjectSymbol => Some(ObjectMirror(o))
    case c: ClassSymbol /*if !refinementClass(c) && !c.isModule*/ => Some(ClassMirror(c))
    case m: MethodSymbol => Some(MethodMirror(m))
    case a: AliasSymbol => error("not implemented")
    case t: TypeSymbol if !t.isParam && !t.name.matches("_\\$\\d+")=> error("not implemented")
    case s => None
  }
}

sealed trait Mirror {
  def sym: Symbol
  lazy val children: Seq[Mirror] = sym.children flatMap {Mirror.of}
  def name = sym.name
  override def toString = "symbol " + name

  def printTree(indent: Int = 0) : Unit = {
    println(("  " * indent) + toString)
    children foreach {_.printTree(indent+1)}
  }
}

case class ClassMirror(sym: ClassSymbol) extends Mirror {
  override def toString = "class " + name
  //def methods: Seq[MethodMirror] =
}

case class ObjectMirror(sym: ObjectSymbol) extends Mirror {
  val TypeRefType(prefix, classSymbol: ClassSymbol, typeArgs) = o.infoType
  override def toString = "object " + name
}

case class MethodMirror(sym: MethodSymbol) extends Mirror {
  override def toString = "def " + name
}