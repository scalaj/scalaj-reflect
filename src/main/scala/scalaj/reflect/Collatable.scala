package scalaj.reflect

import collection.generic.CanBuildFrom

/**
 * Adds the `collate` method to traversable collections.
 *
 * This is a cross between collect and partition, comparing elements against a partial function
 * and extracting matches into a collection of the same type as the PF's return (as `collect` does).
 * But also returning non-matches in a collection with the same type as the input (as `partition` does).
 *
 */
import collection.generic.CanBuildFrom

class Collatable[Repr <% Traversable[T], T] {
//class Collatable[Repr, T](xs: Repr)(implicit bf: CanBuildFrom[Repr, T, Repr], ev: Repr => Traversable[T]) {
  def of(xs: Repr)(implicit rbf: CanBuildFrom[Repr, T, Repr]) = new Of(xs)
  class Of(xs: Repr)(implicit rbf: CanBuildFrom[Repr, T, Repr]) {
    trait Results {
      def remainder: Repr

      type Append[That] <: Results
      def append[That](tup: (That, Repr)): Append[That]

      def andThen[R, That](pf: PartialFunction[T, R])(implicit mbf: CanBuildFrom[Repr, R, That]) = {
        val more = of(remainder).collateOne[R,That](pf)
        append(more)
      }
    }

    case class Results9[M1,M2,M3,M4,M5,M6,M7,M8,M9](
      m1: M1, m2: M2, m3: M3, m4: M4, m5: M5, m6: M6, m7: M7, m8: M8, m9: M9, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, m4, m5, m6, m7, m8, m9, remainder)
      def append[That](tup: (That, Repr)) = error("can't collate beyond 9 elements")
    }

    case class Results8[M1,M2,M3,M4,M5,M6,M7,M8](
      m1: M1, m2: M2, m3: M3, m4: M4, m5: M5, m6: M6, m7: M7, m8: M8, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, m4, m5, m6, m7, m8, remainder)
      type Append[That] = Results9[M1,M2,M3,M4,M5,M6,M7,M8,That]
      def append[That](tup: (That, Repr)) = Results9(m1, m2, m3, m4, m5, m6, m7, m8, tup._1, tup._2)
    }

    case class Results7[M1,M2,M3,M4,M5,M6,M7](
      m1: M1, m2: M2, m3: M3, m4: M4, m5: M5, m6: M6, m7: M7, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, m4, m5, m6, m7, remainder)
      type Append[That] = Results8[M1,M2,M3,M4,M5,M6,M7,That]
      def append[That](tup: (That, Repr)) = Results8(m1, m2, m3, m4, m5, m6, m7, tup._1, tup._2)
    }

    case class Results6[M1,M2,M3,M4,M5,M6](
      m1: M1, m2: M2, m3: M3, m4: M4, m5: M5, m6: M6, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, m4, m5, m6, remainder)
      type Append[That] = Results7[M1,M2,M3,M4,M5,M6,That]
      def append[That](tup: (That, Repr)) = Results7(m1, m2, m3, m4, m5, m6, tup._1, tup._2)
    }

    case class Results5[M1,M2,M3,M4,M5](
      m1: M1, m2: M2, m3: M3, m4: M4, m5: M5, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, m4, m5, remainder)
      type Append[That] = Results6[M1,M2,M3,M4,M5,That]
      def append[That](tup: (That, Repr)) = Results6(m1, m2, m3, m4, m5, tup._1, tup._2)
    }

    case class Results4[M1,M2,M3,M4](
      m1: M1, m2: M2, m3: M3, m4: M4, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, m4, remainder)
      type Append[That] = Results5[M1,M2,M3,M4,That]
      def append[That](tup: (That, Repr)) = Results5(m1, m2, m3, m4, tup._1, tup._2)
    }

    case class Results3[M1,M2,M3](
      m1: M1, m2: M2, m3: M3, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, m3, remainder)
      type Append[That] = Results4[M1,M2,M3,That]
      def append[That](tup: (That, Repr)) = Results4(m1, m2, m3, tup._1, tup._2)
    }

    case class Results2[M1,M2](
      m1: M1, m2: M2, remainder: Repr)
    extends Results {
      implicit def toTuple = (m1, m2, remainder)
      type Append[That] = Results3[M1,M2,That]
      def append[That](tup: (That, Repr)) = Results3(m1, m2, tup._1, tup._2)
    }

    case class Results1[M1](matches: M1, remainder: Repr) extends Results {
      implicit def toTuple = (matches, remainder)

      type Append[That] = Results2[M1, That]
      def append[That](tup: (That, Repr)) = Results2(matches, tup._1, tup._2)
    }

    def collateOne[R, That](pf: PartialFunction[T, R])(implicit mbf: CanBuildFrom[Repr, R, That]) = {
      val matches = mbf(xs)
      val remainder = rbf(xs)
      for (x <- xs) if (pf.isDefinedAt(x)) matches += pf(x) else remainder += x
      (matches.result, remainder.result)
    }

    def collate[R, That](pf: PartialFunction[T, R])(implicit mbf: CanBuildFrom[Repr, R, That]): Results1[That]  = {
      val tup = collateOne[R,That](pf)
      Results1(tup._1, tup._2)
    }
  }
}

object Collatable {
  implicit def traversableIsCollatable[CC[X] <: Traversable[X], T]
  (xs: CC[T])(implicit rbf: CanBuildFrom[CC[T], T, CC[T]]) = new Collatable[CC[T], T] of xs

  implicit def stringIsCollatable(xs: String) = new Collatable[String, Char] of xs
}



