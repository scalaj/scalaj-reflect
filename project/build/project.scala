import sbt._

class ScalajProject(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {
  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
  
  override def compileOptions = Seq(Deprecation, Unchecked)
  override def managedStyle = ManagedStyle.Maven

  val scalap = "org.scala-lang" % "scalap" % "2.8.1" withSources()
  val specs = "org.scala-tools.testing" %% "specs" % "1.6.6"
  val unit = "junit" % "junit" % "4.8.2" % "test"
  //val logback = "ch.qos.logback" % "logback-classic" % "0.9.25"
  //val grizzledSlf4j = "org.clapper" %% "grizzled-slf4j" % "0.3.2"
}
