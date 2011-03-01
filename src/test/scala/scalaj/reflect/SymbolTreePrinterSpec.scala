package scalaj.reflect

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit

import AutographBook._
import tools.scalap.scalax.rules.scalasig._

class SymbolTreePrinterSpec extends SpecificationWithJUnit  {
  "a symbol tree printer" should {
    "print tree of primitive var" in {
      val classMirror = Mirror.ofClass[targets.BasicSample].get

      val varMirror = classMirror.vars.find(_.name=="simpleVar").get

      val getterTree = SymbolTreePrinter.withoutOwners mkTree varMirror.getter
      compareLines(getterTree, ExpectedPrintTrees.simpleVarGetter)

      val setterTree = SymbolTreePrinter.withoutOwners mkTree varMirror.setter
      compareLines(setterTree, ExpectedPrintTrees.simpleVarSetter)
    }

    "print tree of simple method param" in {
      val classMirror = Mirror.ofClass[targets.BasicSample].get

      val method = classMirror.allDefs.find(_.name=="simpleMethod").get
      val params = method.flatParams
      val paramStrings = params map {p => p.name + ": " + p.symType.toString}
      val actualTree = SymbolTreePrinter.withoutOwners mkTree params.head
      compareLines(actualTree, ExpectedPrintTrees.simpleMethodArg)
    }

    "print tree of generic method param" in {
      val classMirror = Mirror.ofClass[targets.BasicSample].get

      val method = classMirror.allDefs.find(_.name=="genericMethod").get
      val params = method.flatParams
      val paramStrings = params map {p => p.name + ": " + p.symType.toString}
      val actualTree = SymbolTreePrinter.withoutOwners mkTree params.head
      compareLines(actualTree, ExpectedPrintTrees.genericMethodArg)
    }

  //  "print tree with nested class" in {
  //    val classMirror = Mirror.ofClass[targets.TweedleDee]
  //    try {
  //      println (classMirror map {SymbolTreePrinter.withoutOwners.mkTree} )
  //    } catch {
  //      case e: Exception =>
  //        println(e.getMessage)
  //        e.printStackTrace
  //    }
  //  }
  //
  //  "print tree with path-dependent alias" in {
  //    val classMirror = Mirror.ofClass[targets.TweedleDo]
  //    try {
  //      //val member = classMirror.allNaturalMethods.find(_.name=="member").get
  //      println (classMirror map {SymbolTreePrinter.withoutOwners.mkTree})
  //    } catch {
  //      case e: Exception =>
  //        println(e.getMessage)
  //        e.printStackTrace
  //    }
  //  }
    def compareLines(actual: String, expected: String): Unit =
      (actual.lines zip expected.lines) foreach {
        case (l,r) => l.trim mustEqual r.trim
      }

  }

}
