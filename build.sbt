name := "unittools"

organization :="fr.janalyse"
homepage := Some(new URL("https://github.com/dacr/unittools"))

scalaVersion := "2.12.4"
scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature", "-language:implicitConversions")
crossScalaVersions := Seq("2.10.6", "2.11.11", "2.12.4")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/junitresults")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)

//publishArtifact in packageDoc := false




pomIncludeRepository := { _ => false }

useGpg := true

licenses += "Apache 2" -> url(s"http://www.apache.org/licenses/LICENSE-2.0.txt")
releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
publishMavenStyle := true
publishArtifact in Test := false
publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging)

scmInfo := Some(ScmInfo(url(s"https://github.com/dacr/unittools"), s"git@github.com:dacr/unittools.git"))

pomExtra in Global := {
  <developers>
    <developer>
      <id>dacr</id>
      <name>David Crosson</name>
      <url>https://github.com/dacr</url>
    </developer>
  </developers>
}


import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    //runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    publishArtifacts,
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  )
 
