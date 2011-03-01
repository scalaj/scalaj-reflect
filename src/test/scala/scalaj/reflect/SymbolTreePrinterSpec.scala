package scalaj.reflect

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit

import AutographBook._
import tools.scalap.scalax.rules.scalasig._

class SymbolTreePrinterSpec extends SpecificationWithJUnit  {

  "print tree of simple method param" in {
    val classMirror = Mirror.ofClass[targets.BasicSample].get

    try {
      val method = classMirror.allDefs.find(_.name=="simpleMethod").get
      val params = method.flatParams
      val paramStrings = params map {p => p.name + ": " + p.symType.toString}
      println("simpleMethod params = " + paramStrings.mkString(", "))
      println("first param =")
      println (SymbolTreePrinter.withoutOwners mkTree params.head)
    } catch {
      case e: Exception =>
        println(e.getMessage)
        e.printStackTrace
    }
  }

  "print tree of generic method param" in {
    val classMirror = Mirror.ofClass[targets.BasicSample].get

    try {
      val method = classMirror.allDefs.find(_.name=="genericMethod").get
      val params = method.flatParams
      val paramStrings = params map {p => p.name + ": " + p.symType.toString}
      println("genericMethod params = " + paramStrings.mkString(", "))
      println("first param =")
      println (SymbolTreePrinter.withoutOwners mkTree params.head)
    } catch {
      case e: Exception =>
        println(e.getMessage)
        e.printStackTrace
    }
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

}
