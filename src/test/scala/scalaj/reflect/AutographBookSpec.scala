package scalaj.reflect

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit

import AutographBook._
import tools.scalap.scalax.rules.scalasig._

class AutographBookSpec extends SpecificationWithJUnit  {


  "the AutographBook" should {
    "correctly resolve the mirror of a class" in {
      val tpe = classOf[targets.BasicSample]
      val sig = sigFromType(tpe)

//    val decompiledText = sig map decompile getOrElse("couldn't decompile")
//    println(decompiledText)
//    println("=====")

      val syms = sig map (topLevelSymsFromSig) getOrElse Nil

      val mirrors = syms map (Mirror.of)
//    mirrors collect { case mcm: MemberContainerMirror => mcm } foreach { mcm =>
//      println(mcm.toString)
//      mcm.allNaturalMethods map (_.toString) foreach {println}
//      mcm.allSyntheticMethods map (_.toString) foreach {println}
//    }
      val tgtMirror = (mirrors collect { case m: ClassMirror => m }).head
      tgtMirror.qualifiedName mustEqual "scalaj.reflect.targets.BasicSample"

    }
  }
}

