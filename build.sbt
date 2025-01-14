name         := "unittools"
organization := "fr.janalyse"
description  := "Durations and bytes size unit made easy for people"

licenses += "NON-AI-APACHE2" -> url(s"https://github.com/non-ai-licenses/non-ai-licenses/blob/main/NON-AI-APACHE2")

scalaVersion       := "3.3.4"
crossScalaVersions := Seq("2.13.15", "3.3.4")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % "test"
)

Test / testOptions += {
  val rel = scalaVersion.value.split("[.]").take(2).mkString(".")
  Tests.Argument(
    "-oDF", // -oW to remove colors
    "-u",
    s"target/junitresults/scala-$rel/"
  )
}

homepage   := Some(url("https://github.com/dacr/unittools"))
scmInfo    := Some(ScmInfo(url(s"https://github.com/dacr/unittools"), s"git@github.com:dacr/unittools.git"))
developers := List(
  Developer(
    id = "dacr",
    name = "David Crosson",
    email = "crosson.david@gmail.com",
    url = url("https://github.com/dacr")
  )
)
