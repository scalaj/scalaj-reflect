package scalaj.reflect

import java.util.regex.Pattern
import tools.scalap.scalax.rules.scalasig._

object NameHelpers {
  val CONSTRUCTOR_NAME = "<init>"

  val _syms = Map(
    "\\$bar" -> "|",
    "\\$tilde" -> "~",
    "\\$bang" -> "!",
    "\\$up" -> "^",
    "\\$plus" -> "+",
    "\\$minus" -> "-",
    "\\$eq" -> "=",
    "\\$less" -> "<",
    "\\$times" -> "*",
    "\\$div" -> "/",
    "\\$bslash" -> "\\\\",
    "\\$greater" -> ">",
    "\\$qmark" -> "?",
    "\\$percent" -> "%",
    "\\$amp" -> "&",
    "\\$colon" -> ":",
    "\\$u2192" -> "?",
    "\\$hash" -> "#")
  val pattern = Pattern.compile(_syms.keys.mkString("", "|", ""))
  val placeholderPattern = "_\\$(\\d)+"

  private def stripPrivatePrefix(name: String) = {
    val i = name.lastIndexOf("$$")
    if (i > 0) name.substring(i + 2) else name
  }

  def processName(name: String) = {
    val stripped = stripPrivatePrefix(name)
    val m = pattern.matcher(stripped)
    var temp = stripped
    while (m.find) {
      val key = m.group
      val re = "\\" + key
      temp = temp.replaceAll(re, _syms(re))
    }
    val result = temp.replaceAll(placeholderPattern, "_")
    scala.reflect.NameTransformer.decode(result)
  }

  def isRefinementClass(c: ClassSymbol) = c.name == "<refinement>"

  def isConstructor(c: Symbol) =
    c.isInstanceOf[MethodSymbol] && c.name == "<init>"

}
