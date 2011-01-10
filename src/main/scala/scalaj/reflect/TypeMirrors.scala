package scalaj.reflect

import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}

sealed abstract trait TypeMirror {
  val symType: SymType
}

case class SimpleTypeMirror(symType: SymType) extends TypeMirror

case class MethodTypeMirror(symType: SymType) extends TypeMirror {

//  def _pmt(mt: Type {def resultType: Type; def paramSymbols: Seq[Symbol]}) = {
//    val paramEntries = mt.paramSymbols.map({
//      case ms: MethodSymbol => ms.name + " : " + toString(ms.infoType)(TypeFlags(true))
//      case _ => "^___^"
//    })
//
//    // Print parameter clauses
//    print(paramEntries.mkString(
//      "(" + (mt match {case _: ImplicitMethodType => "implicit "; case _ => ""})
//      , ", ", ")"))
//
//    // Print result type
//    mt.resultType match {
//      case mt: MethodType => printMethodType(mt, printResult)({})
//      case imt: ImplicitMethodType => printMethodType(imt, printResult)({})
//      case x => if (printResult) {
//        print(" : ");
//        printType(x)
//      }
//    }
//  }

  def processOne(singleSymType: SymType) = singleSymType match {
    //single non-implicit param block
    case mt@MethodType(resType, paramSymbols) => SimpleParamBlockTypeMirror(mt)
    //single implicit param block
    case mt@ImplicitMethodType(resType, paramSymbols) => ImplicitParamBlockTypeMirror(mt)
    //todo: consider other method types
    case x => error(x)
  }

  val blocks: Seq[ParamBlockTypeMirror] = symType match {
    //multiple param blocks
    case pt@PolyType(mt, typeParams) => typeParams map { processOne }
    case x => Seq(processOne(x))
  }

  override def toString = {
    blocks.mkString("(", "", ")")
  }
}

trait ParamBlockTypeMirror {
  def resultType: SymType
  def paramSymbols: Seq[Symbol]

  def params: Seq[ParamMirror] = paramSymbols map {
    case ms: MethodSymbol => ParamMirror(ms)
    case ps => error(ps)
  }
}

case class SimpleParamBlockTypeMirror(symType: MethodType) extends ParamBlockTypeMirror {
  val resultType = symType.resultType
  val paramSymbols = symType.paramSymbols
}

case class ImplicitParamBlockTypeMirror(symType: ImplicitMethodType) extends ParamBlockTypeMirror {
  val resultType = symType.resultType
  val paramSymbols = symType.paramSymbols
}
