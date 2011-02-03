package scalaj.reflect

import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}

import Collatable._

/**
 * Mirrors symbols; anything with a name that can be referenced elsewhere
 */
object Mirror {
  import NameHelpers._
  val cache = collection.mutable.Map.empty[Symbol, Mirror]

  private def generateFor(sym: Symbol): Mirror = sym match {
    case o: ObjectSymbol => ObjectMirror(o)
    case c: ClassSymbol if !isRefinementClass(c) && !c.isModule => ClassMirror(c)
    case m: MethodSymbol => MethodMirror(m)
//    case t: TypeSymbol if !t.isParam && !t.name.matches("_\\$\\d+")=> error("not implemented")
    case x => RawSymbolMirror(x)
  }

  def of(sym: Symbol): Mirror = cache.getOrElseUpdate(sym, generateFor(sym))
}

sealed trait Mirror

/**
 * A symbol is ANY atomic entity with a name, such as:
 * * T
 * * Int
 * * someProperty
 * * aMethod
 * * MyClass
 * * ...
 */
object SymbolMirror {
  def apply(sym: Symbol) = sym match {
    case m: MethodSymbol => MethodMirror(m)
    case t: TypeSymbol => TypeSymbolMirror(t)
    case x => RawSymbolMirror(x)
  }
}

sealed trait SymbolMirror extends Mirror {
  def sym: Symbol
  def name = sym.name
  override def toString = "<<symbol " + name + ": " + sym.getClass.getName + ">>"
}

case class TypeSymbolMirror(sym: TypeSymbol) extends SymbolMirror {
  override def toString = name
}

case class RawSymbolMirror(sym: Symbol) extends SymbolMirror


object TypeMirror {
  def apply(tpe: SymType) = tpe match {
//    case mt: MethodType => MethodMirror(mt)
    case trt: TypeRefType => TypeRefMirror(trt)
    case pt: PolyType => PolyTypeMirror(pt)
    case x => RawTypeMirror(x)
  }
}

sealed trait TypeMirror extends Mirror {
  def tpe: SymType
  override def toString = "<<symbol type:" + tpe.getClass.getName + ">>"
}

case class RawTypeMirror(tpe: SymType) extends TypeMirror

case class TypeRefMirror(tpe: TypeRefType) extends TypeMirror {
  override def toString = tpe.symbol.name
}

case class PolyTypeMirror(tpe: PolyType) extends TypeMirror {
  val typeRef = TypeMirror(tpe.typeRef)
  val rawSymbols = tpe.symbols
  val symbols = rawSymbols map { SymbolMirror(_) }
  override def toString = symbols.mkString("[", ", ", "]") + typeRef.toString
}

/**
 * Anything that can hold *publicly visible* members.
 * "members" being classes, defs, vals, etc.
 */
abstract class MemberContainerMirror extends SymbolMirror {
  def memberSymbols: Seq[Symbol]

  lazy val allMethodSymbols = memberSymbols collect { case ms @ MethodSymbol(_,_) => ms }
  lazy val reallyTrulyAllMethods = allMethodSymbols map { MethodMirror(_) }
  lazy val allNaturalMethods = reallyTrulyAllMethods filterNot { _.isSynthetic }
  lazy val allSyntheticMethods = reallyTrulyAllMethods filter { _.isSynthetic }

//  val (innerClasses, innerObjects, allMethods, others) = members collate {
//    case x: ClassMirror => x
//  } andThen {
//    case x: ObjectMirror => x
//  } andThen {
//    case x: MethodMirror => x
//  } toTuple
//
//  def innerClasses = members collect {}
//  def innerObjects = members collect {  }
//  def allMethods = members collect { case x: MethodMirror => x }
//
//  //when dealing with scala classes and @beanproperty, should only return
//  //the scala-native variants
//  def allAccessors = members collect { case x: MethodMirror if !x.isInstanceOf[PropertyMirror] => x }
//  def beanAccessors = members collect { case x: MethodMirror if !x.isInstanceOf[PropertyMirror] => x }
//  def scalaAccessors = members collect { case x: MethodMirror if !x.isInstanceOf[PropertyMirror] => x }
//
//  def vals = members collect { case x: ValMirror => x }
//  def vars = members collect { case x: VarMirror => x }
}

case class ClassMirror(sym: ClassSymbol) extends MemberContainerMirror {
  val memberSymbols = sym.children
  override def toString = "class " + name
  //def methods: Seq[MethodMirror] =
}

case class ObjectMirror(sym: ObjectSymbol) extends MemberContainerMirror {
  val TypeRefType(prefix, classSymbol: ClassSymbol, typeArgs) = sym.infoType
  val memberSymbols = classSymbol.children
  override def toString = "object " + name
}

/**
 * Anything that takes parameters: Constructors, Setters and Methods
 */
case class MethodMirror(val sym: MethodSymbol) extends SymbolMirror {
  val isSynthetic = sym.isSynthetic
  val isAccessor = sym.isAccessor
  val isConstructor = name == NameHelpers.CONSTRUCTOR_NAME

  val rawType = sym.infoType
  val (refType, typeParamSymbols) = rawType match {
    case PolyType(refType, typeParamSymbols) => (refType, typeParamSymbols)
    case x => (x, Seq.empty[Symbol])
  }

  val typeParams: Seq[SymbolMirror] = typeParamSymbols map SymbolMirror.apply

  private[this] val returnChain = Iterator.iterate(Some(refType): Option[SymType]){
    case Some(MethodType(rt, _))          => Some(rt)
    case Some(ImplicitMethodType(rt, _))  => Some(rt)
    case _                                => None
  } takeWhile (None !=) map (_.get) map {
    case mt: MethodType            => ExplicitParamBlockMirror(mt)
    case imt: ImplicitMethodType   => ImplicitParamBlockMirror(imt)
    case x                         => TypeMirror(x)
  } toList

  val paramBlocks = returnChain collect { case pbm: ParamBlockMirror => pbm }
  val returnType = returnChain.last

  def flatParams = paramBlocks flatMap (_.params)
  override def toString = {
    val prefix = (
      Some("SYN").filter(_ => isSynthetic) ::
      Some("ACC").filter(_ => isAccessor) ::
      Some("CTOR").filter(_ => isConstructor) ::
      Nil).flatten mkString " "

    val typeParamsStr = if (typeParams.isEmpty) "" else typeParams.mkString("[",", ","]")
    val paramBlocksStr = paramBlocks mkString ""
    prefix + "method " + name + typeParamsStr + paramBlocks.mkString("") + ": " + returnType.toString
  }
  // TODO: def invoke(args: Object*)
}

//TODO: DefMirror, ValMirror, VarMirror, ConstructorMirror
/**
 * A val or a var, also (potentially) bean properties
 */
//trait PropertyMirror extends Mirror {
//  def isGettable: Boolean
//  def isSettable: Boolean
//}
//
//case class ValMirror(getter0: MethodMirror) extends PropertyMirror {
//  val getter = Some(getter0)
//  val setter = None
//}
//
//case class VarMirror(sym: Symbol) extends PropertyMirror {
//  val getter = None
//  val setter = None
//
//  private val siblings = sym.parent map {_.children} getOrElse Nil
//  val setter = siblings.collect{
//    case m: MethodSymbol if m.name == sym.name + "_$eq" => m
//  }.head
//}


abstract class ParamBlockMirror(paramSymbols: Seq[Symbol]) extends TypeMirror {
  val params = paramSymbols map {
    case ms: MethodSymbol => ParamMirror(ms)
    case x => FunkyParamMirror(x)
  }
}

case class ExplicitParamBlockMirror(val tpe: MethodType) extends ParamBlockMirror(tpe.paramSymbols) {
  override def toString = params.mkString("(", ", ", ")")
}
case class ImplicitParamBlockMirror(val tpe: ImplicitMethodType) extends ParamBlockMirror(tpe.paramSymbols) {
  override def toString = params.mkString("(implicit ", ", ", ")")
}

case class ParamMirror(sym: MethodSymbol) extends SymbolMirror {
  def symType = TypeMirror(sym.infoType)
  override def toString = name + ": " + symType.toString
}

//normally params are MethodSymbols, I know of no exceptions
//but just in case...
case class FunkyParamMirror(sym: Symbol) extends SymbolMirror {
  override def toString = name + ": ^__^"
}



/**
 * Anything that takes a type. Some classes, methods and aliases
 */
trait TypeParamaterizedMirror extends Mirror {
  def typeParams: Seq[Object]
}




/**
 * Anything that has a constructor.
 * Basically just classes at the moment.
 * (maybe traits in the future, who knows?)
 */
//trait ConstructableMirror extends Mirror {
//  def primaryConstructor: MethodMirror
//  def secondaryConstructors: Seq[MethodMirror]
//  def allConstructors = primaryConstructor +: secondaryConstructors
//}





