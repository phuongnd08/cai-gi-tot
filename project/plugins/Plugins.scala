import sbt._
class MySbtProjectPlugins(info: ProjectInfo) extends PluginDefinition(info) {
  lazy val sbtIdeaRepo = "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
  lazy val sbtIdea = "com.github.mpeltonen" % "sbt-idea-plugin" % "0.1-SNAPSHOT"
}

