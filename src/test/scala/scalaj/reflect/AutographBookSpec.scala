package scalaj.reflect

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit

import AutographBook._
import tools.scalap.scalax.rules.scalasig._

class AutographBookSpec extends SpecificationWithJUnit  {

  "check scala signature annotation is defined" in {
    val tpe = classOf[targets.BasicSample]
    val sig = sigFromType(tpe)

    val decompiledText = sig map decompile getOrElse("couldn't decompile")
    println(decompiledText)
    println("=====")

    val syms = sig map (topLevelSymsFromSig) getOrElse Nil

    val mirrors = syms map (Mirror.of)
    mirrors collect { case mcm: MemberContainerMirror => mcm } foreach { mcm =>
      println(mcm.toString)
      mcm.allNaturalMethods map (_.toString) foreach {println}
      mcm.allSyntheticMethods map (_.toString) foreach {println}
    }
    val tgtMirror = (mirrors collect { case m: ClassMirror => m }).head

    val defs = tgtMirror.allDefs map (_.name)
    println("defs = " + (defs mkString ", "))
    defs mustEqual Seq(
      "simpleMethod",
      "defaultArgMethod",
      "implicitArgMethod",
      "multiArgMethod",
      "multiBlockMethod",
      "genericMethod",
      "viewBoundMethod")

    val constructors = tgtMirror.constructors
    println("constructors = " + (constructors.map(_.method) mkString ", "))
    constructors.size mustEqual 2

    val Seq(ctor1,ctor2) = constructors

    val accessors = tgtMirror.allAccessors map (_.name)
    println("accessors = " + (accessors mkString ", "))
    accessors mustEqual Seq("implicitString", "simpleVar")

    val vals = tgtMirror.vals map (_.name)
    println("vals = " + (vals mkString ", "))
    vals mustEqual Seq("implicitString")

    val vars = tgtMirror.vars map (_.name)
    println("vars = " + (vars mkString ", "))
    vars mustEqual Seq("simpleVar")

    val typeAliases = tgtMirror.typeAliases map (_.name)
    println("typeAliases = " + (typeAliases mkString ", "))
    typeAliases mustEqual Seq("StringAlias")

//    symNames mustEqual Seq("class SampleTarget", "object SampleTarget")
    tgtMirror.toString mustEqual "class BasicSample"

    tgtMirror.name mustEqual "BasicSample"
    tgtMirror.qualifiedName mustEqual "scalaj.reflect.targets.BasicSample"
  }

}

