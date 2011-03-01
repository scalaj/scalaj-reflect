package scalaj.reflect

import org.specs.SpecificationWithJUnit
import Collatable._

class CollatableSpec extends SpecificationWithJUnit  {
  "Collatable" should {
    "collate a List[Int]" in {
      val ints = List(0,9,4,5,-3,-5,6,5,-2,1,0,6,-3,-2)
      val results = ints collate {
        case i: Int if(i < 0) => i.floatValue
      } andThen {
        case i: Int if(i > 5) => i.toString
      } andThen {
        case i: Int if(i == 0) => i
      } toTuple
      val expected = (List(-3.0, -5.0, -2.0, -3.0, -2.0), List("9", "6", "6"), List(0, 0), List(4, 5, 5, 1))
      results mustEqual expected
    }

    "collate a String" in {
      val input = "129sdS DDFJ37$%$w__9 7sSF:::#\t  tWE"
      val results = input collate {
        case c: Char if(c.isDigit) => c
      } andThen {
        case c: Char if(c.isUpper) => c
      } andThen {
        case c: Char if(c.isLower) => c
      } andThen {
        case c: Char if(c.isWhitespace) => c
      } toTuple
      val expected = ("1293797","SDDFJSFWE","sdwst","  \t  ","$%$__:::#")
      results mustEqual expected
    }
  }
}
