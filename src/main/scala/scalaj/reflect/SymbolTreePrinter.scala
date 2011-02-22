package scalaj.reflect
import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}


object SymbolTreePrinter {

  def quote(s: String) = "\"" + s + "\""

  class PrevSeen(symbols: Seq[Symbol] = Seq.empty, types: Seq[SymType] = Seq.empty) {
    def +(s: Symbol): PrevSeen = new PrevSeen(s +: symbols, types)
    def +(t: SymType): PrevSeen = new PrevSeen(symbols, t +: types)

    def contains(s: Symbol): Boolean = symbols contains s
    def contains(t: SymType): Boolean = types contains t

    override def toString = symbols.map(_.path).mkString("symbols: ", ", ", "")
  }

  private def optNest[T](input: (String, Option[T]))(implicit prevSeen: PrevSeen = new PrevSeen) = input match {
    case (name, Some(value)) => nest(name->value)
    case _ => ""
  }

  private def nestAll[T](input: (String, Seq[T]))(implicit prevSeen: PrevSeen = new PrevSeen) = input match {
    case (name, values) => values.zipWithIndex map { case (v,i) =>
      val indexedName = "%s %d/%d".format(name, i+1, values.size)
      nest(indexedName->v)
    } mkString ""
  }

  private def nest[T](input: (String, T))(implicit prevSeen: PrevSeen = new PrevSeen) = {
    val (name, x) = input
    val nested = x match {
      case s: SymbolInfoSymbol if prevSeen contains s =>  s.path + " (already seen at #" + s.entry.index + ")"
      case s: Symbol if prevSeen contains s =>  s.path + " (already seen)"
      case t: SymType if prevSeen contains t => "(type already seen)"
      case null => "<null>"
      case _ => mkTree(x)
    }
    (name + " = " + nested).lines map {"|  " +  _} mkString("\n","\n","")
  }

  def mkTree[T](input: T)(implicit prevSeen: PrevSeen = new PrevSeen): String = input match {
    case sm: SymbolMirror => mkTree(sm.sym)
    case sym: Symbol => mkSymbolTree(sym)(prevSeen + sym)
    case tpe: SymType => mkTypeTree(tpe)(prevSeen + tpe)
    case i: Int => i.toString
    case s: String => s
    case e: ScalaSig#Entry => e.toString
    case _ => "oops @ " + input.toString + ": " + input.asInstanceOf[AnyRef].getClass
  }
  
  def mkTypeTree(tpe: SymType)(implicit prevSeen: PrevSeen = new PrevSeen): String = tpe match {
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
      "TypeRefType prefix = " +
        nest("prefix"->prefix) +
        nest("symbol"->symbol) +
        nestAll("typeArg"->typeArgs)
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
        nestAll("typeRef"->typeRefs)
    case ClassInfoTypeWithCons(symbol, typeRefs, cons) =>
      "ClassInfoTypeWithCons" +
        nest("symbol"->symbol) +
        nestAll("typeRef"->typeRefs) +
        nest("cons"->cons)
    case MethodType(resultType, paramSymbols) =>
      "MethodType" +
        nest("resultType"->resultType) +
        nestAll("paramSymbol"->paramSymbols)
    case PolyType(typeRef, symbols) =>
      "PolyType" +
        nest("typeRef"->typeRef) +
        nestAll("symbol"->symbols)
    case PolyTypeWithCons(typeRef, symbols, cons) =>
      "PolyTypeWithCons" +
        nest("typeRef"->typeRef) +
        nestAll("symbol"->symbols) +
        nest("cons"->cons)
    case ImplicitMethodType(resultType, paramSymbols) =>
      "ImplicitMethodType" +
        nest("resultType"->resultType) +
        nestAll("paramSymbol"->paramSymbols)
    case AnnotatedType(typeRef, attribTreeRefs) =>
      "AnnotatedType" +
        nest("typeRef"->typeRef) +
        nestAll("attribTreeRef"->attribTreeRefs)
    case AnnotatedWithSelfType(typeRef, symbol, attribTreeRefs) =>
      "AnnotatedWithSelfType" +
        nest("typeRef"->typeRef) +
        nest("symbol"->symbol) +
        nestAll("attribTreeRef"->attribTreeRefs)
    case DeBruijnIndexType(typeLevel, typeIndex) =>
      "DeBruijnIndexType" +
        nest("typeLevel"->typeLevel) +
        nest("typeIndex"->typeIndex)
    case ExistentialType(typeRef, symbols) =>
      "ExistentialType" +
        nest("typeRef"->typeRef) +
        nestAll("symbol"->symbols)
  }

  def mkSymbolTree(sym: Symbol)(implicit prevSeen: PrevSeen = new PrevSeen): String = sym match {
    case NoSymbol => "NoSymbol"
    case ExternalSymbol(name, parent, entry) =>
      quote(sym.path) + " (external #" + entry.index + ")"
    case sym @ TypeSymbol(_) => "TypeSymbol" + addSymbolInfo(sym)
    case sym @ AliasSymbol(_) => "AliasSymbol" + addSymbolInfo(sym)
    case sym @ ClassSymbol(_, thisTypeRef) => "ClassSymbol" + addSymbolInfo(sym) +
        optNest("thisTypeRef"->thisTypeRef)
    case sym @ ObjectSymbol(_) => "ObjectSymbol" + addSymbolInfo(sym)
    case sym @ MethodSymbol(_, aliasRef) => "MethodSymbol"  + addSymbolInfo(sym) +
        optNest("aliasRef"->aliasRef)
  }

  def addSymbolInfo(sym: SymbolInfoSymbol)(implicit prevSeen: PrevSeen = new PrevSeen): String = {
    val SymbolInfo(name, owner, flags, privateWithin, info, entry) = sym.symbolInfo
    " name = " + quote(name) + " (entry #" + entry.index + ")" +
    nest("path"->quote(sym.path)) +
    nest("owner"->owner) +
    nest("flags/info"->(flags.toString + " / " + info.toString)) +
    optNest("privateWithin"->privateWithin) +
    nest("infoType"->sym.infoType)
  }


}
