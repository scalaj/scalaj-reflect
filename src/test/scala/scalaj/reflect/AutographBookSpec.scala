package scalaj.reflect

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit

import AutographBook._


class AutographBookSpec extends SpecificationWithJUnit  {

  "check scala signature annotation is defined" in {
    val tpe = classOf[SampleTarget]
    val sig = sigFromType(tpe)

    val decompiledText = sig map decompile getOrElse("couldn't decompile")
    println(decompiledText)
    println("=====")

    val syms = sig map (symsFromSig) getOrElse Nil

    val mirrors = syms map (Mirror.of)
    mirrors collect { case mcm: MemberContainerMirror => mcm } foreach { mcm =>
      println(mcm.toString)
      mcm.reallyTrulyAllMethods map (_.toString) foreach {println}
    }
    val symNames = mirrors map { _.toString }
//    symNames mustEqual Seq("class SampleTarget", "object SampleTarget")
    symNames mustEqual Seq("class SampleTarget")
  }

}

