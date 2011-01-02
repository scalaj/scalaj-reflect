package scalaj.reflect

import tools.scalap.scalax.rules.scalasig._
import reflect.ScalaSignature
import scala.reflect.generic.ByteCodecs

class AutographBook {
  def someMethod = 42
}

object AutographBook {
  case class Foo(x: Int)

  def decodeSigBytes(bytes: Array[Byte]): Array[Byte] = {
    val length = ByteCodecs.decode(bytes)
    bytes.take(length)
  }

  def sigBytesFromAnnotation(sig: ScalaSignature) = decodeSigBytes(sig.bytes.getBytes)

  def sigBytesFromType(tpe: Class[_]) = {
    tpe.getAnnotations.view collect { case x: ScalaSignature => x } map {sigBytesFromAnnotation} headOption
  }

  def sigFromBytes(bytes: Array[Byte]) = ScalaSigAttributeParsers.parse(ByteCode(bytes))

  def sigFromType(tpe: Class[_]) = sigBytesFromType(tpe) map (sigFromBytes)

  def symsFromSig(s: ScalaSig) = s.topLevelClasses ++ s.topLevelObjects
}