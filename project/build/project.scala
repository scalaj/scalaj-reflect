import sbt._
import de.element34.sbteclipsify._


class ScalajProject(info: ProjectInfo) extends DefaultProject(info)
with IdeaProject
with Eclipsify
with posterous.Publish {
  override def compileOptions = Seq(Deprecation, Unchecked)

  //Dependencies
  val scalap = "org.scala-lang" % "scalap" % "2.8.1" withSources()
  val specs = "org.scala-tools.testing" %% "specs" % "1.6.6"
  val unit = "junit" % "junit" % "4.8.2" % "test"
  //val logback = "ch.qos.logback" % "logback-classic" % "0.9.25"
  //val grizzledSlf4j = "org.clapper" %% "grizzled-slf4j" % "0.3.2"
  
  // Publishing
  override def managedStyle = ManagedStyle.Maven
  val publishTo = "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/snapshots/"
  Credentials(Path.userHome / ".ivy2" / ".credentials", log)
  override def publishAction = super.publishAction && publishCurrentNotes
  override def extraTags = "scalaj" :: super.extraTags

}
