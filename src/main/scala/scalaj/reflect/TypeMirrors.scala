package scalaj.reflect

import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}
import tools.scalap.scalax.util.StringUtil

//object TypeMirror {
//  def of(symType: SymType): TypeMirror = symType match {
//    case trt: TypeRefType => RefTypeMirror(trt)
//    case pt: PolyType => PolyTypeMirror(pt)
//    case mt: MethodType => MethodTypeMirror(mt)
//    case mt: ImplicitMethodType => MethodTypeMirror(mt)
//    case x => UnknownTypeMirror(x)
//  }
//}

//sealed abstract trait TypeMirror {
//  val symType: SymType
//}
//
//case class PolyTypeMirror(symType: PolyType) extends TypeMirror {
//  val innerSymMirror = TypeMirror.of(symType.typeRef)
//  val typeSymbols = symType.symbols map Mirror.of
//  def typesStr = typeSymbols map {_.toString} mkString ","
//  override def toString = "<POLYt" + typesStr + ">" + innerSymMirror.toString
//}
//
//case class SimpleTypeMirror(symType: SymType) extends TypeMirror {
//  import NameHelpers._
//
//  def innerToString(t: SymType, sep: String) = symType match {
//    case ThisType(symbol) => sep + processName(symbol.path) + ".type"
//    case SingleType(typeRef, symbol) => sep + processName(symbol.path) + ".type"
//    case ConstantType(constant) => sep + (constant match {
//      case null => "Null"
//      case _: Unit => "Unit"
//      case _: Boolean => "Boolean"
//      case _: Byte => "Byte"
//      case _: Char => "Char"
//      case _: Short => "Short"
//      case _: Int => "Int"
//      case _: Long => "Long"
//      case _: Float => "Float"
//      case _: Double => "Double"
//      case _: String => "String"
//      case c: Class[_] => "Class[" + c.getComponentType.getCanonicalName.replace("$", ".") + "]"
//    })
////    case TypeRefType(prefix, symbol, typeArgs) => sep + (symbol.path match {
////      case "scala.<repeated>" => flags match {
////        case TypeFlags(true) => toString(typeArgs.head) + "*"
////        case _ => "scala.Seq" + typeArgString(typeArgs)
////      }
////      case "scala.<byname>" => "=> " + toString(typeArgs.head)
////      case _ => {
////        val path = StringUtil.cutSubstring(symbol.path)(".package") //remove package object reference
////        StringUtil.trimStart(processName(path) + typeArgString(typeArgs), "<empty>.")
////      }
////    })
////    case TypeBoundsType(lower, upper) => {
////      val lb = toString(lower)
////      val ub = toString(upper)
////      val lbs = if (!lb.equals("scala.Nothing")) " >: " + lb else ""
////      val ubs = if (!ub.equals("scala.Any")) " <: " + ub else ""
////      lbs + ubs
////    }
////    case RefinedType(classSym, typeRefs) => sep + typeRefs.map(toString).mkString("", " with ", "")
////    case ClassInfoType(symbol, typeRefs) => sep + typeRefs.map(toString).mkString(" extends ", " with ", "")
////    case ClassInfoTypeWithCons(symbol, typeRefs, cons) => sep + typeRefs.map(toString).
////            mkString(cons + " extends ", " with ", "")
////
////    case ImplicitMethodType(resultType, _) => toString(resultType, sep)
////    case MethodType(resultType, _) => toString(resultType, sep)
////
////    case PolyType(typeRef, symbols) => typeParamString(symbols) + toString(typeRef, sep)
////    case PolyTypeWithCons(typeRef, symbols, cons) => typeParamString(symbols) + processName(cons) + toString(typeRef, sep)
////    case AnnotatedType(typeRef, attribTreeRefs) => {
////      toString(typeRef, sep)
////    }
////    case AnnotatedWithSelfType(typeRef, symbol, attribTreeRefs) => toString(typeRef, sep)
////    //case DeBruijnIndexType(typeLevel, typeIndex) =>
////    case ExistentialType(typeRef, symbols) => {
////      val refs = symbols.map(toString _).filter(!_.startsWith("_")).map("type " + _)
////      toString(typeRef, sep) + (if (refs.size > 0) refs.mkString(" forSome {", "; ", "}") else "")
////    }
//    case _ => sep + "<simplet>" + t.toString
//  }
//
//  override def toString = innerToString(symType, "")
//}
//
//case class RefTypeMirror(symType: TypeRefType) extends TypeMirror {
//  val targetSymbol = Mirror.of(symType.symbol)
//  override def toString = "<reft>" + targetSymbol.toString
//}
//
//case class UnknownTypeMirror(symType: SymType) extends TypeMirror {
//  override def toString = "<UNKNOWNt>" + symType.toString
//}
//
//case class MethodTypeMirror(symType: SymType) extends TypeMirror {
//
//  val typeParams: Seq[TypeParamMirror] = symType match {
//    //multiple param blocks
//    case pt@PolyType(mt, typeParams) => typeParams map { TypeParamMirror.apply }
//    case _ => Seq.empty
//  }
//
//  val isImplicit = symType.isInstanceOf[ImplicitMethodType]
//
//  val resultType: TypeMirror = symType match {
//    case MethodType(rt, _) => MethodTypeMirror(rt)
//    case ImplicitMethodType(rt, _) => MethodTypeMirror(rt)
//    case rt @ TypeRefType(_, _, _) => RefTypeMirror(rt)
//    case x => SimpleTypeMirror(x)
//  }
//
//  def paramToMirror(param: Symbol) = param match {
//    case ms: MethodSymbol => ParamMirror(ms)
//    case _ => UnknownMirror(param)
//  }
//
//  val paramSymbols = symType match {
//    case MethodType(_, ps) => ps map {paramToMirror}
//    case ImplicitMethodType(_, ps) => ps map {paramToMirror}
//    case x => Seq.empty
//  }
//
//  def resultToString = resultType match {
//    case MethodTypeMirror(_) => resultType.toString
//    case _ => ": " + resultType.toString
//  }
//
//  override def toString = {
//    "<methodt>" + (if (paramSymbols.isEmpty) {
//      ""
//    } else {
//      paramSymbols.map{_.toString}.mkString(
//        if(isImplicit) "(implicit " else "(",
//        ",",
//        ")"
//      )
//    }) + resultToString
//  }
//}
//
//trait ParamBlockTypeMirror {
//  def resultType: SymType
//  def paramSymbols: Seq[Symbol]
//
//  def params: Seq[ParamMirror] = paramSymbols map {
//    case ms: MethodSymbol => ParamMirror(ms)
//    case ps => error(ps.toString)
//  }
//}
//
//case class SimpleParamBlockTypeMirror(symType: MethodType) extends ParamBlockTypeMirror {
//  val resultType = symType.resultType
//  val paramSymbols = symType.paramSymbols
//}
//
//case class ImplicitParamBlockTypeMirror(symType: ImplicitMethodType) extends ParamBlockTypeMirror {
//  val resultType = symType.resultType
//  val paramSymbols = symType.paramSymbols
//}
