package scalaj.reflect

import tools.scalap.scalax.rules.scalasig.{Type => SymType, _}

trait TypeMirror(st: SymType)

case class MethodTypeMirror(st: SymType) extends TypeMirror(st) {
  val blocks: Seq[ParamBlockTypeMirror] = Nil

  def _pmt(mt: Type {def resultType: Type; def paramSymbols: Seq[Symbol]}) = {
    val paramEntries = mt.paramSymbols.map({
      case ms: MethodSymbol => ms.name + " : " + toString(ms.infoType)(TypeFlags(true))
      case _ => "^___^"
    })

    // Print parameter clauses
    print(paramEntries.mkString(
      "(" + (mt match {case _: ImplicitMethodType => "implicit "; case _ => ""})
      , ", ", ")"))

    // Print result type
    mt.resultType match {
      case mt: MethodType => printMethodType(mt, printResult)({})
      case imt: ImplicitMethodType => printMethodType(imt, printResult)({})
      case x => if (printResult) {
        print(" : ");
        printType(x)
      }
    }
  }

  t match {
    case mt@MethodType(resType, paramSymbols) => _pmt(mt)
    case mt@ImplicitMethodType(resType, paramSymbols) => _pmt(mt)
    case pt@PolyType(mt, typeParams) => {
      print(typeParamString(typeParams))
      printMethodType(mt, printResult)({})
    }
    //todo consider another method types
    case x => print(" : "); printType(x)
  }

}

case class ParamBlockTypeMirror(mt: MethodType) extends TypeMirror(mt) {

}

