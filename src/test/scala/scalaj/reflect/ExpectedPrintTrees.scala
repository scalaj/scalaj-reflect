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
     >|  |  |  |  parent = scala (already seen at #20)""".stripMargin('>')

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
     >|  child 1/1 = scalaj.reflect.targets.BasicSample.simpleVar_$eq.x$1 (already seen at #83)""".stripMargin('>')

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
     >|  |  |  |  parent = scala (already seen at #20)""".stripMargin('>')

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


  val basicSampleClass =
  """>ClassSymbol name = "BasicSample" (entry #0)
     >|  path = "scalaj.reflect.targets.BasicSample"
     >|  owner = "scalaj.reflect.targets" (external #2 type = EXTMODCLASSref[10])
     >|  |  parent = "scalaj.reflect" (external #4 type = EXTMODCLASSref[10])
     >|  |  |  parent = "scalaj" (external #6 type = EXTMODCLASSref[10])
     >|  flags/info = 1073741824 / 9
     >|  infoType = ClassInfoType
     >|  |  symbol = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  typeRef 1/2 = TypeRefType
     >|  |  |  prefix = ThisType
     >|  |  |  |  symbol = "java.lang" (external #12 type = EXTMODCLASSref[10])
     >|  |  |  |  |  parent = "java" (external #14 type = EXTMODCLASSref[10])
     >|  |  |  symbol = "java.lang.Object" (external #16 type = EXTref[9])
     >|  |  |  |  parent = java.lang (already seen at #12)
     >|  |  typeRef 2/2 = TypeRefType
     >|  |  |  prefix = ThisType
     >|  |  |  |  symbol = "scala" (external #20 type = EXTMODCLASSref[10])
     >|  |  |  symbol = "scala.ScalaObject" (external #22 type = EXTref[9])
     >|  |  |  |  parent = scala (already seen at #20)
     >|  child 1/19 = MethodSymbol name = "param1" (entry #24)
     >|  |  path = "scalaj.reflect.targets.BasicSample.param1"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 537395204 / 26
     >|  |  infoType = TypeRefType
     >|  |  |  prefix = SingleType
     >|  |  |  |  typeRef = scala(type already seen)
     >|  |  |  |  symbol = "scala.Predef" (external #28 type = EXTref[9])
     >|  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  symbol = "scala.Predef.String" (external #30 type = EXTref[9])
     >|  |  |  |  parent = "scala.Predef" (external #32 type = EXTMODCLASSref[10])
     >|  |  |  |  |  parent = scala (already seen at #20)
     >|  child 2/19 = MethodSymbol name = "param2" (entry #33)
     >|  |  path = "scalaj.reflect.targets.BasicSample.param2"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 537395204 / 35
     >|  |  infoType = TypeRefType
     >|  |  |  prefix = scala(type already seen)
     >|  |  |  symbol = "scala.Int" (external #36 type = EXTref[9])
     >|  |  |  |  parent = scala (already seen at #20)
     >|  child 3/19 = MethodSymbol name = "<init>" (entry #38)
     >|  |  path = "scalaj.reflect.targets.BasicSample.<init>"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 40
     >|  |  infoType = MethodType
     >|  |  |  resultType = TypeRefType
     >|  |  |  |  prefix = ThisType
     >|  |  |  |  |  symbol = scalaj.reflect.targets (already seen at #2)
     >|  |  |  |  symbol = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  |  paramSymbol 1/2 = MethodSymbol name = "param1" (entry #43)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.<init>.param1"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.<init> (already seen at #38)
     >|  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  |  paramSymbol 2/2 = MethodSymbol name = "param2" (entry #44)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.<init>.param2"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.<init> (already seen at #38)
     >|  |  |  |  flags/info = 8192 / 35
     >|  |  |  |  infoType = scala.Int(type already seen)
     >|  |  child 1/2 = scalaj.reflect.targets.BasicSample.<init>.param1 (already seen at #43)
     >|  |  child 2/2 = scalaj.reflect.targets.BasicSample.<init>.param2 (already seen at #44)
     >|  child 4/19 = MethodSymbol name = "<init>" (entry #45)
     >|  |  path = "scalaj.reflect.targets.BasicSample.<init>"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 46
     >|  |  infoType = MethodType
     >|  |  |  resultType = scalaj.reflect.targets.BasicSample(type already seen)
     >|  |  |  paramSymbol 1/1 = MethodSymbol name = "param2a" (entry #47)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.<init>.param2a"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.<init> (already seen at #45)
     >|  |  |  |  flags/info = 8192 / 49
     >|  |  |  |  infoType = TypeRefType
     >|  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  symbol = "scala.Float" (external #50 type = EXTref[9])
     >|  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  child 1/1 = scalaj.reflect.targets.BasicSample.<init>.param2a (already seen at #47)
     >|  child 5/19 = AliasSymbol name = "StringAlias" (entry #52)
     >|  |  path = "StringAlias"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 1073741824 / 26
     >|  |  infoType = scala.Predef.String(type already seen)
     >|  child 6/19 = ClassSymbol name = "Foo" (entry #54)
     >|  |  path = "scalaj.reflect.targets.BasicSample.Foo"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 1073741824 / 56
     >|  |  infoType = ClassInfoType
     >|  |  |  symbol = scalaj.reflect.targets.BasicSample.Foo (already seen at #54)
     >|  |  |  typeRef 1/2 = java.lang.Object(type already seen)
     >|  |  |  typeRef 2/2 = scala.ScalaObject(type already seen)
     >|  |  child 1/3 = MethodSymbol name = "param" (entry #57)
     >|  |  |  path = "scalaj.reflect.targets.BasicSample.Foo.param"
     >|  |  |  owner = scalaj.reflect.targets.BasicSample.Foo (already seen at #54)
     >|  |  |  flags/info = 675283456 / 59
     >|  |  |  infoType = PolyType
     >|  |  |  |  typeRef = scala.Predef.String(type already seen)
     >|  |  child 2/3 = MethodSymbol name = "param " (entry #60)
     >|  |  |  path = "scalaj.reflect.targets.BasicSample.Foo.param "
     >|  |  |  owner = scalaj.reflect.targets.BasicSample.Foo (already seen at #54)
     >|  |  |  flags/info = 537395204 / 26
     >|  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  child 3/3 = MethodSymbol name = "<init>" (entry #62)
     >|  |  |  path = "scalaj.reflect.targets.BasicSample.Foo.<init>"
     >|  |  |  owner = scalaj.reflect.targets.BasicSample.Foo (already seen at #54)
     >|  |  |  flags/info = 512 / 63
     >|  |  |  infoType = MethodType
     >|  |  |  |  resultType = TypeRefType
     >|  |  |  |  |  prefix = ThisType
     >|  |  |  |  |  |  symbol = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  |  |  |  symbol = scalaj.reflect.targets.BasicSample.Foo (already seen at #54)
     >|  |  |  |  paramSymbol 1/1 = MethodSymbol name = "param" (entry #66)
     >|  |  |  |  |  path = "scalaj.reflect.targets.BasicSample.Foo.<init>.param"
     >|  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.Foo.<init> (already seen at #62)
     >|  |  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  |  child 1/1 = scalaj.reflect.targets.BasicSample.Foo.<init>.param (already seen at #66)
     >|  child 7/19 = MethodSymbol name = "implicitString" (entry #67)
     >|  |  path = "scalaj.reflect.targets.BasicSample.implicitString"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 138412545 / 69
     >|  |  infoType = PolyType
     >|  |  |  typeRef = TypeRefType
     >|  |  |  |  prefix = java.lang(type already seen)
     >|  |  |  |  symbol = "java.lang.String" (external #71 type = EXTref[9])
     >|  |  |  |  |  parent = java.lang (already seen at #12)
     >|  child 8/19 = MethodSymbol name = "implicitString " (entry #72)
     >|  |  path = "scalaj.reflect.targets.BasicSample.implicitString "
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 524292 / 70
     >|  |  infoType = java.lang.String(type already seen)
     >|  child 9/19 = MethodSymbol name = "simpleVar" (entry #74)
     >|  |  path = "scalaj.reflect.targets.BasicSample.simpleVar"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 134218240 / 76
     >|  |  infoType = PolyType
     >|  |  |  typeRef = scala.Int(type already seen)
     >|  child 10/19 = MethodSymbol name = "simpleVar_$eq" (entry #77)
     >|  |  path = "scalaj.reflect.targets.BasicSample.simpleVar_$eq"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 134218240 / 79
     >|  |  infoType = MethodType
     >|  |  |  resultType = TypeRefType
     >|  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  symbol = "scala.Unit" (external #81 type = EXTref[9])
     >|  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  paramSymbol 1/1 = MethodSymbol name = "x$1" (entry #83)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.simpleVar_$eq.x$1"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.simpleVar_$eq (already seen at #77)
     >|  |  |  |  flags/info = 2105344 / 35
     >|  |  |  |  infoType = scala.Int(type already seen)
     >|  |  child 1/1 = scalaj.reflect.targets.BasicSample.simpleVar_$eq.x$1 (already seen at #83)
     >|  child 11/19 = MethodSymbol name = "simpleVar " (entry #85)
     >|  |  path = "scalaj.reflect.targets.BasicSample.simpleVar "
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 528388 / 35
     >|  |  infoType = scala.Int(type already seen)
     >|  child 12/19 = MethodSymbol name = "simpleMethod" (entry #87)
     >|  |  path = "scalaj.reflect.targets.BasicSample.simpleMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 89
     >|  |  infoType = MethodType
     >|  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  paramSymbol 1/1 = MethodSymbol name = "arg" (entry #90)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.simpleMethod.arg"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.simpleMethod (already seen at #87)
     >|  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  child 1/1 = scalaj.reflect.targets.BasicSample.simpleMethod.arg (already seen at #90)
     >|  child 13/19 = MethodSymbol name = "defaultArgMethod" (entry #92)
     >|  |  path = "scalaj.reflect.targets.BasicSample.defaultArgMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 94
     >|  |  infoType = MethodType
     >|  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  paramSymbol 1/1 = MethodSymbol name = "arg" (entry #95)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.defaultArgMethod.arg"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.defaultArgMethod (already seen at #92)
     >|  |  |  |  flags/info = 33562624 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  child 1/1 = scalaj.reflect.targets.BasicSample.defaultArgMethod.arg (already seen at #95)
     >|  child 14/19 = MethodSymbol name = "implicitArgMethod" (entry #96)
     >|  |  path = "scalaj.reflect.targets.BasicSample.implicitArgMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 98
     >|  |  infoType = ImplicitMethodType
     >|  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  paramSymbol 1/1 = MethodSymbol name = "arg" (entry #99)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.implicitArgMethod.arg"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.implicitArgMethod (already seen at #96)
     >|  |  |  |  flags/info = 8193 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  child 1/1 = scalaj.reflect.targets.BasicSample.implicitArgMethod.arg (already seen at #99)
     >|  child 15/19 = MethodSymbol name = "multiArgMethod" (entry #100)
     >|  |  path = "scalaj.reflect.targets.BasicSample.multiArgMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 102
     >|  |  infoType = MethodType
     >|  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  paramSymbol 1/2 = MethodSymbol name = "arg1" (entry #103)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.multiArgMethod.arg1"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.multiArgMethod (already seen at #100)
     >|  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  |  paramSymbol 2/2 = MethodSymbol name = "arg2" (entry #105)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.multiArgMethod.arg2"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.multiArgMethod (already seen at #100)
     >|  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  child 1/2 = scalaj.reflect.targets.BasicSample.multiArgMethod.arg1 (already seen at #103)
     >|  |  child 2/2 = scalaj.reflect.targets.BasicSample.multiArgMethod.arg2 (already seen at #105)
     >|  child 16/19 = MethodSymbol name = "multiBlockMethod" (entry #107)
     >|  |  path = "scalaj.reflect.targets.BasicSample.multiBlockMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 109
     >|  |  infoType = MethodType
     >|  |  |  resultType = MethodType
     >|  |  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  |  paramSymbol 1/1 = MethodSymbol name = "arg2" (entry #111)
     >|  |  |  |  |  path = "scalaj.reflect.targets.BasicSample.multiBlockMethod.arg2"
     >|  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.multiBlockMethod (already seen at #107)
     >|  |  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  |  paramSymbol 1/1 = MethodSymbol name = "arg1" (entry #112)
     >|  |  |  |  path = "scalaj.reflect.targets.BasicSample.multiBlockMethod.arg1"
     >|  |  |  |  owner = scalaj.reflect.targets.BasicSample.multiBlockMethod (already seen at #107)
     >|  |  |  |  flags/info = 8192 / 26
     >|  |  |  |  infoType = scala.Predef.String(type already seen)
     >|  |  child 1/2 = scalaj.reflect.targets.BasicSample.multiBlockMethod.arg2 (already seen at #111)
     >|  |  child 2/2 = scalaj.reflect.targets.BasicSample.multiBlockMethod.arg1 (already seen at #112)
     >|  child 17/19 = MethodSymbol name = "genericMethod" (entry #113)
     >|  |  path = "scalaj.reflect.targets.BasicSample.genericMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 115
     >|  |  infoType = PolyType
     >|  |  |  typeRef = MethodType
     >|  |  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  |  paramSymbol 1/1 = MethodSymbol name = "arg" (entry #117)
     >|  |  |  |  |  path = "scalaj.reflect.targets.BasicSample.genericMethod.arg"
     >|  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.genericMethod (already seen at #113)
     >|  |  |  |  |  flags/info = 8192 / 118
     >|  |  |  |  |  infoType = TypeRefType
     >|  |  |  |  |  |  prefix = NoPrefixType
     >|  |  |  |  |  |  symbol = TypeSymbol name = "T" (entry #120)
     >|  |  |  |  |  |  |  path = "T"
     >|  |  |  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.genericMethod (already seen at #113)
     >|  |  |  |  |  |  |  flags/info = 1073750272 / 122
     >|  |  |  |  |  |  |  infoType = TypeBoundsType
     >|  |  |  |  |  |  |  |  lower = TypeRefType
     >|  |  |  |  |  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  |  |  |  |  symbol = "scala.Nothing" (external #124 type = EXTref[9])
     >|  |  |  |  |  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  |  |  |  |  |  upper = TypeRefType
     >|  |  |  |  |  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  |  |  |  |  symbol = "scala.Any" (external #127 type = EXTref[9])
     >|  |  |  |  |  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  symbol 1/1 = T (already seen at #120)
     >|  |  child 1/2 = scalaj.reflect.targets.BasicSample.genericMethod.arg (already seen at #117)
     >|  |  child 2/2 = T (already seen at #120)
     >|  child 18/19 = MethodSymbol name = "viewBoundMethod" (entry #129)
     >|  |  path = "scalaj.reflect.targets.BasicSample.viewBoundMethod"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 512 / 131
     >|  |  infoType = PolyType
     >|  |  |  typeRef = MethodType
     >|  |  |  |  resultType = ImplicitMethodType
     >|  |  |  |  |  resultType = java.lang.String(type already seen)
     >|  |  |  |  |  paramSymbol 1/1 = MethodSymbol name = "evidence$1" (entry #134)
     >|  |  |  |  |  |  path = "scalaj.reflect.targets.BasicSample.viewBoundMethod.evidence$1"
     >|  |  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.viewBoundMethod (already seen at #129)
     >|  |  |  |  |  |  flags/info = 8193 / 136
     >|  |  |  |  |  |  infoType = TypeRefType
     >|  |  |  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  |  |  symbol = "scala.Function1" (external #137 type = EXTref[9])
     >|  |  |  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  |  |  |  |  typeArg 1/2 = TypeRefType
     >|  |  |  |  |  |  |  |  prefix = NoPrefixType(type already seen)
     >|  |  |  |  |  |  |  |  symbol = TypeSymbol name = "T" (entry #140)
     >|  |  |  |  |  |  |  |  |  path = "T"
     >|  |  |  |  |  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.viewBoundMethod (already seen at #129)
     >|  |  |  |  |  |  |  |  |  flags/info = 1073750272 / 122
     >|  |  |  |  |  |  |  |  |  infoType = scala.Nothing <: scala.Any(type already seen)
     >|  |  |  |  |  |  |  typeArg 2/2 = TypeRefType
     >|  |  |  |  |  |  |  |  prefix = SingleType
     >|  |  |  |  |  |  |  |  |  typeRef = SingleType
     >|  |  |  |  |  |  |  |  |  |  typeRef = ThisType
     >|  |  |  |  |  |  |  |  |  |  |  symbol = "<no symbol>.<root>" (external #145 type = EXTMODCLASSref[10])
     >|  |  |  |  |  |  |  |  |  |  |  |  parent = NoSymbol
     >|  |  |  |  |  |  |  |  |  |  symbol = "scala" (external #147 type = EXTref[9])
     >|  |  |  |  |  |  |  |  |  symbol = "scala.package" (external #148 type = EXTref[9])
     >|  |  |  |  |  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  |  |  |  |  |  symbol = "scala.package.Ordered" (external #150 type = EXTref[9])
     >|  |  |  |  |  |  |  |  |  parent = "scala.package" (external #152 type = EXTMODCLASSref[10])
     >|  |  |  |  |  |  |  |  |  |  parent = scala (already seen at #20)
     >|  |  |  |  |  |  |  |  typeArg 1/1 = T(type already seen)
     >|  |  |  |  paramSymbol 1/1 = MethodSymbol name = "arg" (entry #153)
     >|  |  |  |  |  path = "scalaj.reflect.targets.BasicSample.viewBoundMethod.arg"
     >|  |  |  |  |  owner = scalaj.reflect.targets.BasicSample.viewBoundMethod (already seen at #129)
     >|  |  |  |  |  flags/info = 8192 / 139
     >|  |  |  |  |  infoType = T(type already seen)
     >|  |  |  symbol 1/1 = T (already seen at #140)
     >|  |  child 1/3 = scalaj.reflect.targets.BasicSample.viewBoundMethod.evidence$1 (already seen at #134)
     >|  |  child 2/3 = T (already seen at #140)
     >|  |  child 3/3 = scalaj.reflect.targets.BasicSample.viewBoundMethod.arg (already seen at #153)
     >|  child 19/19 = MethodSymbol name = "defaultArgMethod$default$1" (entry #154)
     >|  |  path = "scalaj.reflect.targets.BasicSample.defaultArgMethod$default$1"
     >|  |  owner = scalaj.reflect.targets.BasicSample (already seen at #0)
     >|  |  flags/info = 35652096 / 156
     >|  |  infoType = PolyType
     >|  |  |  typeRef = AnnotatedType
     >|  |  |  |  typeRef = scala.Predef.String(type already seen)
     >|  |  |  |  attribTreeRef 1/1 = 158""".stripMargin('>')

  val polymorphicSampleClass =
  """>ClassSymbol name = "PolymorphicSample" (entry #0)
     >|  path = "scalaj.reflect.targets.PolymorphicSample"
     >|  owner = "scalaj.reflect.targets" (external #2 type = EXTMODCLASSref[10])
     >|  |  parent = "scalaj.reflect" (external #4 type = EXTMODCLASSref[10])
     >|  |  |  parent = "scalaj" (external #6 type = EXTMODCLASSref[10])
     >|  flags/info = 128 / 9
     >|  infoType = PolyType
     >|  |  typeRef = ClassInfoType
     >|  |  |  symbol = scalaj.reflect.targets.PolymorphicSample (already seen at #0)
     >|  |  |  typeRef 1/2 = TypeRefType
     >|  |  |  |  prefix = ThisType
     >|  |  |  |  |  symbol = "java.lang" (external #13 type = EXTMODCLASSref[10])
     >|  |  |  |  |  |  parent = "java" (external #15 type = EXTMODCLASSref[10])
     >|  |  |  |  symbol = "java.lang.Object" (external #17 type = EXTref[9])
     >|  |  |  |  |  parent = java.lang (already seen at #13)
     >|  |  |  typeRef 2/2 = TypeRefType
     >|  |  |  |  prefix = ThisType
     >|  |  |  |  |  symbol = "scala" (external #21 type = EXTMODCLASSref[10])
     >|  |  |  |  symbol = "scala.ScalaObject" (external #23 type = EXTref[9])
     >|  |  |  |  |  parent = scala (already seen at #21)
     >|  |  symbol 1/1 = TypeSymbol name = "T" (entry #32)
     >|  |  |  path = "T"
     >|  |  |  owner = scalaj.reflect.targets.PolymorphicSample (already seen at #0)
     >|  |  |  flags/info = 1073750272 / 34
     >|  |  |  infoType = TypeBoundsType
     >|  |  |  |  lower = TypeRefType
     >|  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  symbol = "scala.Nothing" (external #36 type = EXTref[9])
     >|  |  |  |  |  |  parent = scala (already seen at #21)
     >|  |  |  |  upper = TypeRefType
     >|  |  |  |  |  prefix = scala(type already seen)
     >|  |  |  |  |  symbol = "scala.Any" (external #39 type = EXTref[9])
     >|  |  |  |  |  |  parent = scala (already seen at #21)
     >|  child 1/4 = MethodSymbol name = "<init>" (entry #25)
     >|  |  path = "scalaj.reflect.targets.PolymorphicSample.<init>"
     >|  |  owner = scalaj.reflect.targets.PolymorphicSample (already seen at #0)
     >|  |  flags/info = 512 / 27
     >|  |  infoType = MethodType
     >|  |  |  resultType = TypeRefType
     >|  |  |  |  prefix = ThisType
     >|  |  |  |  |  symbol = scalaj.reflect.targets (already seen at #2)
     >|  |  |  |  symbol = scalaj.reflect.targets.PolymorphicSample (already seen at #0)
     >|  |  |  |  typeArg 1/1 = TypeRefType
     >|  |  |  |  |  prefix = NoPrefixType
     >|  |  |  |  |  symbol = T (already seen at #32)
     >|  child 2/4 = T (already seen at #32)
     >|  child 3/4 = MethodSymbol name = "sampleVal" (entry #41)
     >|  |  path = "scalaj.reflect.targets.PolymorphicSample.sampleVal"
     >|  |  owner = scalaj.reflect.targets.PolymorphicSample (already seen at #0)
     >|  |  flags/info = 768 / 43
     >|  |  infoType = PolyType
     >|  |  |  typeRef = T(type already seen)
     >|  child 4/4 = MethodSymbol name = "method" (entry #44)
     >|  |  path = "scalaj.reflect.targets.PolymorphicSample.method"
     >|  |  owner = scalaj.reflect.targets.PolymorphicSample (already seen at #0)
     >|  |  flags/info = 512 / 46
     >|  |  infoType = PolyType
     >|  |  |  typeRef = MethodType
     >|  |  |  |  resultType = TypeRefType
     >|  |  |  |  |  prefix = NoPrefixType(type already seen)
     >|  |  |  |  |  symbol = TypeSymbol name = "T" (entry #49)
     >|  |  |  |  |  |  path = "T"
     >|  |  |  |  |  |  owner = scalaj.reflect.targets.PolymorphicSample.method (already seen at #44)
     >|  |  |  |  |  |  flags/info = 1073750272 / 34
     >|  |  |  |  |  |  infoType = scala.Nothing <: scala.Any(type already seen)
     >|  |  |  |  paramSymbol 1/1 = MethodSymbol name = "x" (entry #50)
     >|  |  |  |  |  path = "scalaj.reflect.targets.PolymorphicSample.method.x"
     >|  |  |  |  |  owner = scalaj.reflect.targets.PolymorphicSample.method (already seen at #44)
     >|  |  |  |  |  flags/info = 8192 / 48
     >|  |  |  |  |  infoType = T(type already seen)
     >|  |  |  symbol 1/1 = T (already seen at #49)
     >|  |  child 1/2 = T (already seen at #49)
     >|  |  child 2/2 = scalaj.reflect.targets.PolymorphicSample.method.x (already seen at #50)""".stripMargin('>')

}
