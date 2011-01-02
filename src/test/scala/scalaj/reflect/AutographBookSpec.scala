package scalaj.reflect

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit

import AutographBook._


class AutographBookSpec extends SpecificationWithJUnit  {

  "check scala signature annotation is defined" in {
    val tpe = classOf[AutographBook]
    println(tpe)

    val sig = sigFromType(tpe)
    val syms = sig map (symsFromSig) getOrElse Nil

    val mirrors = syms flatMap (Mirror.of)
    mirrors foreach { _.printTree() }
    val symNames = mirrors map { _.toString }
    symNames mustEqual Seq("class AutographBook", "object AutographBook")
  }

}

