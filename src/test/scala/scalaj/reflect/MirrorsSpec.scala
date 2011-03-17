package scalaj.reflect


import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit
import reflect.Manifest

class MirrorsSpec  extends SpecificationWithJUnit {
  val tgtMirror = Mirror.ofClass[targets.BasicSample].get

  "a class mirror" should {
    "reflect a dynamically sourced Class[_] instance" in {
      val dynMirror = Mirror.ofClass(Class.forName("scalaj.reflect.targets.BasicSample"))
      dynMirror map { m => m.name mustEqual "BasicSample" }
    }

    "locate all defs" in {

      val defs = tgtMirror.allDefs map (_.name)
  //    println("defs = " + (defs mkString ", "))
      defs mustEqual Seq(
        "simpleMethod",
        "defaultArgMethod",
        "implicitArgMethod",
        "multiArgMethod",
        "multiBlockMethod",
        "genericMethod",
        "viewBoundMethod")
    }

    "locate all constructors" in {
      val constructors = tgtMirror.constructors
  //    println("constructors = " + (constructors.map(_.method) mkString ", "))
      constructors.size mustEqual 2

      val Seq(ctor1,ctor2) = constructors
    }

    "locate all accessors" in {
      val accessors = tgtMirror.allAccessors map (_.name)
  //    println("accessors = " + (accessors mkString ", "))
      accessors mustEqual Seq("implicitString", "simpleVar")
    }

    "locate all vals" in {
      val vals = tgtMirror.vals map (_.name)
  //    println("vals = " + (vals mkString ", "))
      vals mustEqual Seq("implicitString")
    }

    "locate all vars" in {
      val vars = tgtMirror.vars map (_.name)
  //    println("vars = " + (vars mkString ", "))
      vars mustEqual Seq("simpleVar")
    }

    "locate all type aliases" in {
      val typeAliases = tgtMirror.typeAliases map (_.name)
  //    println("typeAliases = " + (typeAliases mkString ", "))
      typeAliases mustEqual Seq("StringAlias")
    }

    "correctly display the name of the mirrored class" in {
  //    symNames mustEqual Seq("class SampleTarget", "object SampleTarget")
      tgtMirror.toString mustEqual "class BasicSample"

      tgtMirror.name mustEqual "BasicSample"
      tgtMirror.qualifiedName mustEqual "scalaj.reflect.targets.BasicSample"
    }

    "locate default type params for a generic polymorphic mirrored class" in {
      val mirror = Mirror.ofClass[targets.PolymorphicSample[_]].get
      val actual = mirror.typeParams
      val expected = Seq(Manifest.wildcardType(Manifest.Nothing, Manifest.Any))
      actual mustEqual expected
    }

    "generate a manifest for a simple (monomorphic) mirrored class" in {
      val mani = tgtMirror.manifest
      mani mustEqual manifest[targets.BasicSample]
    }

    "generate a manifest for a wildcard polymorphic mirrored class" in {
      val mirror = Mirror.ofClass[targets.PolymorphicSample[_]].get
      val actual = mirror.manifest
      val expected = manifest[targets.PolymorphicSample[_]]
      actual mustEqual expected
    }

    "directly refine a generic polymorphic mirrored class" in {
      val mirror = Mirror.ofClass[targets.PolymorphicSample[_]].get
      val refined = mirror.reify(manifest[Int])
      val actual = refined.manifest
      val expected = manifest[targets.PolymorphicSample[Int]]
      actual mustEqual expected
    }

    "generate a manifest for a refined polymorphic mirrored class" in {
      val mirror = Mirror.ofClass[targets.PolymorphicSample[Int]].get
      val actual = mirror.manifest
      val expected = manifest[targets.PolymorphicSample[Int]]
      actual mustEqual expected
    }

//    "generate a manifest for a nested polymorphic mirrored class" in {
//      val mirror = Mirror.ofClass[targets.NestedPolymorphicSample[Int]#Inner].get
//      val actual = mirror.manifest
//      val expected = manifest[targets.NestedPolymorphicSample[Int]#Inner]
//      actual mustEqual expected
//    }
  }

}
