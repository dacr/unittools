name := "unittools"
organization :="fr.janalyse"
homepage := Some(new URL("https://github.com/dacr/unittools"))
licenses += "Apache 2" -> url(s"http://www.apache.org/licenses/LICENSE-2.0.txt")
scmInfo := Some(ScmInfo(url(s"https://github.com/dacr/unittools"), s"git@github.com:dacr/unittools.git"))

scalaVersion := "2.13.1"
scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature", "-language:implicitConversions")

crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.11", "2.13.1")
// 2.10.7  : generates java 6 bytecodes
// 2.11.12 : generates java 6 bytecodes
// 2.12.11 : generates java 8 bytecodes && JVM8 required for compilation
// 2.13.1  : generates java 8 bytecodes && JVM8 required for compilation


testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/junitresults")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)

testOptions in Test += {
  val rel = scalaVersion.value.split("[.]").take(2).mkString(".")
  Tests.Argument(
    "-oDF", // -oW to remove colors
    "-u", s"target/junitresults/scala-$rel/"
  )
}
