package scalaj.reflect

import tools.scalap.scalax.rules.scalasig._

object Mirror {
  private def refinementClass(c: ClassSymbol) = c.name == "<refinement>"
  
  def of(sym: Symbol): Option[Mirror] = sym match {
    case o: ObjectSymbol => Some(ObjectMirror(o))
    case c: ClassSymbol if !refinementClass(c) && !c.isModule => Some(ClassMirror(c))
    case m: MethodSymbol => Some(MethodMirror(m))
    case a: AliasSymbol => error("not implemented")
    case t: TypeSymbol if !t.isParam && !t.name.matches("_\\$\\d+")=> error("not implemented")
    case s => None
  }
}

sealed trait Mirror {
  def sym: Symbol
  def childSymbols = sym.children
  lazy val children: Seq[Mirror] = childSymbols flatMap {Mirror.of}
  def name = sym.name
  override def toString = "symbol " + name

  def printTree(indent: Int = 0) : Unit = {
    println(("  " * indent) + toString)
    children foreach {_.printTree(indent+1)}
  }
}

abstract class ModuleMirror(sym: Symbol) extends Mirror {
  private[this] val CONSTRUCTOR_NAME = "<init>"
  
  def classSymbol: ClassSymbol
  override def childSymbols = classSymbol.children
  def constructors = children.collect{ case m: MethodMirror if m.name  == CONSTRUCTOR_NAME => m }
  def innerObjects = children.collect{ case o: ObjectMirror => o }
  def innerClasses = children.collect{ case c: ClassMirror => c }
  def innerModules = innerObjects ++ innerClasses
  def methods = children.collect{ case m: MethodMirror if m.name  != CONSTRUCTOR_NAME => m}

  override def printTree(indent: Int = 0) : Unit = {
    println(("  " * indent) + toString)
    constructors foreach {_.printTree(indent+1)}
    innerObjects foreach {_.printTree(indent+1)}
    innerClasses foreach {_.printTree(indent+1)}
    methods foreach {_.printTree(indent+1)}
  }

}

case class ClassMirror(sym: ClassSymbol) extends ModuleMirror(sym) {
  val classSymbol = sym
  override def toString = "class " + name
  //def methods: Seq[MethodMirror] =
}

case class ObjectMirror(sym: ObjectSymbol) extends ModuleMirror(sym) {
  val TypeRefType(prefix, classSymbol: ClassSymbol, typeArgs) = sym.infoType
  override def toString = "object " + name
}

case class MethodMirror(sym: MethodSymbol) extends Mirror {
  override def toString = "def " + name
  override def printTree(indent: Int = 0) : Unit = {
    println(("  " * indent) + toString)
  }
}




