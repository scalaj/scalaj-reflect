package scalaj.reflect
object ExpectedPrintTrees {
  val simpleVarGetter =
  """>MethodSymbol name = "simpleVar" (entry #74)
     >|  path = "scalaj.reflect.targets.BasicSample.simpleVar"
     >|  flags/info = 134218240 / 76
     >|  infoType = PolyType
     >|  |  typeRef = TypeRefType
     >|  |  |  prefix = ThisType
     >|  |  |  |  symbol = "scala" (external #20 type = EXTMODCLASSref[10])
     >|  |  |  symbol = "scala.Int" (external #36 type = EXTref[9])
     >|  |  |  |  parent = scala (already seen at #20)""".stripMargin('>').replace("\\r","")

  val simpleVarSetter =
  """>MethodSymbol name = "simpleVar_$eq" (entry #77)
     >|  path = "scalaj.reflect.targets.BasicSample.simpleVar_$eq"
     >|  flags/info = 134218240 / 79
     >|  infoType = MethodType
     >|  |  resultType = TypeRefType
     >|  |  |  prefix = ThisType
     >|  |  |  |  symbol = "scala" (external #20 type = EXTMODCLASSref[10])
     >|  |  |  symbol = "scala.Unit" (external #81 type = EXTref[9])
     >|  |  |  |  parent = scala (already seen at #20)
     >|  |  paramSymbol 1/1 = MethodSymbol name = "x$1" (entry #83)
     >|  |  |  path = "scalaj.reflect.targets.BasicSample.simpleVar_$eq.x$1"
     >|  |  |  flags/info = 2105344 / 35
     >|  |  |  infoType = TypeRefType
     >|  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  symbol = "scala.Int" (external #36 type = EXTref[9])
     >|  |  |  |  |  parent = scala (already seen at #20)
     >|  child 1/1 = scalaj.reflect.targets.BasicSample.simpleVar_$eq.x$1 (already seen at #83)""".stripMargin('>').replace("\\r","")

  val simpleMethodArg =
  """>MethodSymbol name = "arg" (entry #90)
     >|  path = "scalaj.reflect.targets.BasicSample.simpleMethod.arg"
     >|  flags/info = 8192 / 26
     >|  infoType = TypeRefType
     >|  |  prefix = SingleType
     >|  |  |  typeRef = ThisType
     >|  |  |  |  symbol = "scala" (external #20 type = EXTMODCLASSref[10])
     >|  |  |  symbol = "scala.Predef" (external #28 type = EXTref[9])
     >|  |  |  |  parent = scala (already seen at #20)
     >|  |  symbol = "scala.Predef.String" (external #30 type = EXTref[9])
     >|  |  |  parent = "scala.Predef" (external #32 type = EXTMODCLASSref[10])
     >|  |  |  |  parent = scala (already seen at #20)""".stripMargin('>').replace("\\r","")

  val genericMethodArg =
  """>MethodSymbol name = "arg" (entry #117)
     >|  path = "scalaj.reflect.targets.BasicSample.genericMethod.arg"
     >|  flags/info = 8192 / 118
     >|  infoType = TypeRefType
     >|  |  prefix = NoPrefixType
     >|  |  symbol = TypeSymbol name = "T" (entry #120)
     >|  |  |  path = "T"
     >|  |  |  flags/info = 1073750272 / 122
     >|  |  |  infoType = TypeBoundsType
     >|  |  |  |  lower = TypeRefType
     >|  |  |  |  |  prefix = ThisType
     >|  |  |  |  |  |  symbol = "scala" (external #20 type = EXTMODCLASSref[10])
     >|  |  |  |  |  symbol = "scala.Nothing" (external #124 type = EXTref[9])
     >|  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  |  upper = TypeRefType
     >|  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  symbol = "scala.Any" (external #127 type = EXTref[9])
     >|  |  |  |  |  |  parent = scala (already seen at #20)""".stripMargin('>').replace("\\r","")
}
