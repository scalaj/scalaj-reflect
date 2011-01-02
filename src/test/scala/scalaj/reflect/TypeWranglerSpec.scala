package scalaj.spring

//give the wrangler a short name, don't import methods as we want full control without using them as implicits
import scalaj.reflect.{TypeWrangler => wrangler}
import org.specs.SpecificationWithJUnit


class TypeWranglerSpec extends SpecificationWithJUnit  {
  "dynamically lookup a Seq[String] manifest" in {
    val actual : Manifest[_] = wrangler.manifestOf(classOf[Seq[String]])
    val expected : Manifest[_] = manifest[Seq[String]]

    //actual.toString mustEqual expected.toString
    //actual mustEqual expected
  }
}