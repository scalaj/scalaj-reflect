package scalaj.reflect
import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}


object SymbolTreePrinter {
  def quote(s: String) = "\"" + s + "\""

  def mkTree[T](input: T) = (new Run).mkTree(input)

  case class Context(skipChildren: Boolean = false)  {
    def withoutChildren: Context = this.copy(skipChildren=true)
  }

  class Run {
    var symbolsSeen: Set[Symbol] = Set.empty
    var typesSeen: Set[SymType] = Set.empty

    def mkTree[T](input: T)(implicit ctx: Context = new Context): String = input match {
      case sm: SymbolMirror => mkTree(sm.sym)
      case sym: Symbol => symbolsSeen += sym; mkSymbolTree(sym)
      case tpe: SymType => typesSeen += tpe; mkTypeTree(tpe)
      case i: Int => i.toString
      case s: String => s
      case e: ScalaSig#Entry => e.toString
      case _ => "oops @ " + input.toString + ": " + input.asInstanceOf[AnyRef].getClass
    }

    private def optNest[T](input: (String, Option[T]))(implicit ctx: Context = new Context) = input match {
      case (name, Some(value)) => nest(name->value)
      case _ => ""
    }

    private def nestAll[T](input: (String, Seq[T]))(implicit ctx: Context = new Context) = input match {
      case (name, values) => values.zipWithIndex map { case (v,i) =>
        val indexedName = "%s %d/%d".format(name, i+1, values.size)
        nest(indexedName->v)
      } mkString ""
    }

    private def nest[T](input: (String, T))(implicit ctx: Context = new Context) = {
      val (name, x) = input
      val nested = x match {
        case s: ScalaSigSymbol if symbolsSeen contains s =>  s.path + " (already seen at #" + s.entry.index + ")"
        case s: Symbol if symbolsSeen contains s =>  s.path + " (already seen)"
        case t: SymType if typesSeen contains t => typeName(t) + "(type already seen)"
        case null => "<null>"
        case _ => mkTree(x)
      }
      (name + " = " + nested).lines map {"|  " +  _} mkString("\n","\n","")
    }

    def mkTypeTree(tpe: SymType)(implicit ctx: Context = new Context): String = tpe match {
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

    def mkSymbolTree(sym: Symbol)(implicit ctx: Context = new Context): String = sym match {
      case NoSymbol => "NoSymbol"
      case ExternalSymbol(name, parent, entry) =>
        quote(sym.path) +
          " (external #" + entry.index + " type = " + entryTypeName(entry.entryType) + ")" +
          optNest("parent"->parent)(ctx.withoutChildren) +
          (if(!ctx.skipChildren) nestAll("child"->sym.children) else "")

      case sym @ TypeSymbol(_) => "TypeSymbol" + addSymbolInfo(sym)
      case sym @ AliasSymbol(_) => "AliasSymbol" + addSymbolInfo(sym)
      case sym @ ClassSymbol(_, thisTypeRef) => "ClassSymbol" + addSymbolInfo(sym) +
          optNest("thisTypeRef"->thisTypeRef)
      case sym @ ObjectSymbol(_) => "ObjectSymbol" + addSymbolInfo(sym)
      case sym @ MethodSymbol(_, aliasRef) => "MethodSymbol"  + addSymbolInfo(sym) +
          optNest("aliasRef"->aliasRef)
    }

    def addSymbolInfo(sym: SymbolInfoSymbol)(implicit ctx: Context = new Context): String = {
      val SymbolInfo(name, owner, flags, privateWithin, info, entry) = sym.symbolInfo
      " name = " + quote(name) + " (entry #" + entry.index + ")" +
      nest("path"->quote(sym.path)) +
      nest("owner"->owner)(ctx.withoutChildren) +
      nest("flags/info"->(flags.toString + " / " + info.toString)) +
      optNest("privateWithin"->privateWithin) +
      nest("infoType"->sym.infoType) +
      nestAll("child"->sym.children)
    }
  }

  def typeName(tpe: SymType): String = tpe match {
    case NoType => "NoType"
    case NoPrefixType => "NoPrefixType"
    case ThisType(symbol) => symbol.path
    case SingleType(_, symbol) => symbol.path
    case ConstantType(constant) => constant.toString
    case TypeRefType(prefix, symbol, typeArgs) => symbol.path
    case TypeBoundsType(lower, upper) => typeName(lower) + " <: " + typeName(upper)
    case RefinedType(classSym, typeRefs) => classSym.path
    case ClassInfoType(symbol, typeRefs) => symbol.path
    case ClassInfoTypeWithCons(symbol, typeRefs, cons) => symbol.path
    case MethodType(resultType, paramSymbols) => "MethodType"
    case PolyType(typeRef, symbols) => "PolyType"
    case PolyTypeWithCons(typeRef, symbols, cons) => "PolyTypeWithCons"
    case ImplicitMethodType(resultType, paramSymbols) => "ImplicitMethodType"
    case AnnotatedType(typeRef, attribTreeRefs) => "AnnotatedType"
    case AnnotatedWithSelfType(typeRef, symbol, attribTreeRefs) => symbol.path
    case DeBruijnIndexType(typeLevel, typeIndex) => "DeBruijnIndexType"
    case ExistentialType(typeRef, symbols) => "ExistentialType"
  }

  val entryTypeName = Map(
    1->"TERMNAME",
    2->"TYPENAME",
    3->"NONEsym",
    4->"TYPEsym",
    5->"ALIASsym",
    6->"CLASSsym",
    7->"MODULEsym",
    8->"VALsym",
    9->"EXTref",
    10->"EXTMODCLASSref",
    11->"NOtpe",
    12->"NOPREFIXtpe",
    13->"THIStpe",
    14->"SINGLEtpe",
    15->"CONSTANTtpe",
    16->"TYPEREFtpe",
    17->"TYPEBOUNDStpe",
    18->"REFINEDtpe",
    19->"CLASSINFOtpe",
    20->"METHODtpe",
    21->"POLYTtpe",
    22->"IMPLICITMETHODtpe",
    52->"SUPERtpe",
    24->"LITERALunit",
    25->"LITERALboolean",
    26->"LITERALbyte",
    27->"LITERALshort",
    28->"LITERALchar",
    29->"LITERALint",
    30->"LITERALlong",
    31->"LITERALfloat",
    32->"LITERALdouble",
    33->"LITERALstring",
    34->"LITERALnull",
    35->"LITERALclass",
    36->"LITERALenum",
    40->"SYMANNOT",
    41->"CHILDREN",
    42->"ANNOTATEDtpe",
    43->"ANNOTINFO",
    44->"ANNOTARGARRAY",
    47->"DEBRUIJNINDEXtpe",
    48->"EXISTENTIALtpe") map {case (a,b) => a -> "%s[%s]".format(b,a)}

}
