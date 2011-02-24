package scalaj.reflect

import tools.scalap.scalax.rules.scalasig._
import reflect.ScalaSignature
import scala.reflect.generic.ByteCodecs

object AutographBook {

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

  def decompile(s: ScalaSig) =
    tools.scalap.Main.parseScalaSignature(s, false)

  def safeGetClass(name: String): Option[Class[_]] = {
    try Some(Class forName name) catch { case e: Exception => None }
  }

  /**
   * Given a string, will return a `Class[_] -> String` representing the longest possible
   * match that's a valid class name, paired with the remainder of the string.
   */

  def resolveClassAndRemainder(path: String) = {
    val splitPoints = path.zipWithIndex collect {case ('.',i) => i}
    val splits = (path->"") +: (splitPoints.reverse map {path.splitAt} map { case (a,b) => (a,b.tail)})
    splits.view flatMap { case (l,r) => safeGetClass(l) map (_ -> r) } headOption
  }

  def resolveExternal(path: String) = {
    resolveClassAndRemainder(path) map { resolv =>
      val (cls, remainder) = resolv
      val sig = sigFromType(cls)
      val syms = sig.toSeq flatMap { _.symbols } collect {
        case sym: AliasSymbol if sym.name == remainder => sym
      }
      syms
    }
  }

  def deAlias(sym: AliasSymbol) = {
    sym.infoType
  }

}
