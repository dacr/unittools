name := "unittools"

version := "0.2.5-SNAPSHOT"

organization :="fr.janalyse"

organizationHomepage := Some(new URL("http://www.janalyse.fr"))

scalaVersion := "2.10.5"

crossScalaVersions := Seq("2.10.5", "2.11.7")

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature", "-language:implicitConversions")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.+" % "test"
)

publishArtifact in packageDoc := false

