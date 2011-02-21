package scalaj.reflect
import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}


object SymbolTreePrinter {

  private def optNest[T](input: (String, Option[T])) = input match {
    case (name, Some(value)) => nest(name->value)
    case _ => ""
  }

  private def nest[T](input: (String, T)) = {
    val (name, x) = input
    val nested = x match {
      case sym: Symbol => mkSymbolTree(sym)
      case si: SymbolInfo => mkSymbolInfoTree(si)
      case tpe: SymType => mkTypeTree(tpe)
      case i: Int => i.toString
      case s: String => s
      case e: ScalaSig#Entry => e.toString
      case _ => "oops @ " + x.toString + ": " + x.asInstanceOf[AnyRef].getClass
    }
//    val nested = mkTree(x)
    (name + " = " + nested).lines map {"|  " +  _} mkString("\n","\n","")
  }

  def mkTree[T](input: T) = input match {
      case sis: SymbolInfoSymbol => mkTypeTree(sis.infoType)
      case sym: Symbol => mkSymbolTree(sym)
      case si: SymbolInfo => mkSymbolInfoTree(si)
      case tpe: SymType => mkTypeTree(tpe)
      case i: Int => i.toString
      case s: String => s
      case e: ScalaSig#Entry => e.toString
      case _ => "oops @ " + input.toString + ": " + input.asInstanceOf[AnyRef].getClass
  }
  
  def mkTypeTree(tpe: SymType): String = tpe match {
    case NoType => "NoType"
    case NoPrefixType => "NoPrefixType"
    case ThisType(symbol) =>
      "ThisType" +
        nest("symbol"->symbol)
    case SingleType(typeRef, symbol) =>
      "SingleType" +
        nest("typeRef"->typeRef) +
        nest("symbol"->symbol)
    case ConstantType(constant) =>
      "ConstantType" +
        nest("constant"->constant)
    case TypeRefType(prefix, symbol, typeArgs) =>
      "TypeRefType" +
        nest("prefix"->prefix) +
        nest("symbol"->symbol) +
        (typeArgs map { ta => nest("typeArg"->ta) } mkString "")
    case TypeBoundsType(lower, upper) =>
      "TypeBoundsType" +
        nest("lower"->lower) +
        nest("upper"->upper)
    case RefinedType(classSym, typeRefs) =>
      "RefinedType" +
        nest("classSym"->classSym) +
        nest("typeRefs"->typeRefs)
    case ClassInfoType(symbol, typeRefs) =>
      "ClassInfoType" +
        nest("symbol"->symbol) +
        nest("typeRefs"->typeRefs)
    case ClassInfoTypeWithCons(symbol, typeRefs, cons) =>
      "ClassInfoTypeWithCons" +
        nest("symbol"->symbol) +
        nest("typeRefs"->typeRefs) +
        nest("cons"->cons)
    case MethodType(resultType, paramSymbols) =>
      "MethodType" +
        nest("resultType"->resultType) +
        nest("paramSymbols"->paramSymbols)
    case PolyType(typeRef, symbols) =>
      "PolyType" +
        nest("typeRef"->typeRef) +
        nest("symbols"->symbols)
    case PolyTypeWithCons(typeRef, symbols, cons) =>
      "PolyTypeWithCons" +
        nest("typeRef"->typeRef) +
        nest("symbols"->symbols) +
        nest("cons"->cons)
    case ImplicitMethodType(resultType, paramSymbols) =>
      "ImplicitMethodType" +
        nest("resultType"->resultType) +
        nest("paramSymbols"->paramSymbols)
    case AnnotatedType(typeRef, attribTreeRefs) =>
      "AnnotatedType" +
        nest("typeRef"->typeRef) +
        nest("attribTreeRefs"->attribTreeRefs)
    case AnnotatedWithSelfType(typeRef, symbol, attribTreeRefs) =>
      "AnnotatedWithSelfType" +
        nest("typeRef"->typeRef) +
        nest("symbol"->symbol) +
        nest("attribTreeRefs"->attribTreeRefs)
    case DeBruijnIndexType(typeLevel, typeIndex) =>
      "DeBruijnIndexType" +
        nest("typeLevel"->typeLevel) +
        nest("typeIndex"->typeIndex)
    case ExistentialType(typeRef, symbols) =>
      "ExistentialType" +
        nest("typeRef"->typeRef) +
        nest("symbols"->symbols)
  }

  def mkSymbolTree(sym: Symbol): String = sym match {
    case NoSymbol => "NoSymbol"
    case ExternalSymbol(name, parent, entry) =>
      "ExternalSymbol" +
        nest("path"->sym.path) +
        nest("name"->name) +
        optNest("parent"->parent) +
        nest("entry"->entry)
    case TypeSymbol(symbolInfo) =>
      "TypeSymbol" +
        nest("path"->sym.path) +
        nest("symbolInfo"->symbolInfo)
    case AliasSymbol(symbolInfo) =>
      "AliasSymbol" +
        nest("path"->sym.path) +
        nest("symbolInfo"->symbolInfo)
    case ClassSymbol(symbolInfo, thisTypeRef) =>
      "ClassSymbol" +
        nest("path"->sym.path) +
        nest("symbolInfo"->symbolInfo) +
        optNest("thisTypeRef"->thisTypeRef)
    case ObjectSymbol(symbolInfo) =>
      "ObjectSymbol" +
        nest("path"->sym.path) +
        nest("symbolInfo"->symbolInfo)
    case MethodSymbol(symbolInfo, aliasRef) =>
      "MethodSymbol" +
        nest("path"->sym.path) +
        nest("symbolInfo"->symbolInfo) +
        optNest("aliasRef"->aliasRef)
  }

  def mkSymbolInfoTree(syminfo: SymbolInfo): String = {
    val SymbolInfo(name, owner, flags, privateWithin, info, entry) = syminfo
    "SymbolInfo" +
      nest("name"->name) +
      nest("owner"->owner) +
      nest("flags"->flags) +
      optNest("privateWithin"->privateWithin) +
      nest("info"->info) +
      nest("entry"->entry)
  }

}
