name         := "unittools"
organization := "fr.janalyse"
homepage     := Some(new URL("https://github.com/dacr/unittools"))
scmInfo      := Some(ScmInfo(url(s"https://github.com/dacr/unittools"), s"git@github.com:dacr/unittools.git"))

licenses += "NON-AI-APACHE2" -> url(s"https://github.com/non-ai-licenses/non-ai-licenses/blob/main/NON-AI-APACHE2")

scalaVersion       := "3.3.0"
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions")
crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.18", "2.13.11", "3.3.0")
// 2.10.x : generates java 6 bytecodes
// 2.11.x : generates java 6 bytecodes
// 2.12.x : generates java 8 bytecodes && JVM8 required for compilation
// 2.13.x : generates java 8 bytecodes && JVM8 required for compilation

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % "test"
)

Test / testOptions += {
  val rel = scalaVersion.value.split("[.]").take(2).mkString(".")
  Tests.Argument(
    "-oDF", // -oW to remove colors
    "-u",
    s"target/junitresults/scala-$rel/"
  )
}
