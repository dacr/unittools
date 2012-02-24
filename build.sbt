name := "unittools"

version := "0.2"

organization :="fr.janalyse"

organizationHomepage := Some(new URL("http://www.janalyse.fr"))

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.8.1", "2.8.2", "2.9.1")

libraryDependencies <<= (scalaVersion, libraryDependencies) { (sv, deps) =>
  val versionMap = Map("2.8.1" -> "1.5.1",   "2.8.2" -> "1.5.1",   "2.9.1" -> "1.6.1")
  val testVersion = versionMap.getOrElse(sv, error("Unsupported Scala version " + sv))
  deps :+ ("org.scalatest" %% "scalatest" % testVersion % "test")
}

publishTo := Some(
     Resolver.sftp(
         "JAnalyse Repository",
         "www.janalyse.fr",
         "/home/tomcat/webapps-janalyse/repository"
     ) as("tomcat", new File(util.Properties.userHome+"/.ssh/id_rsa"))
)

publishArtifact in packageDoc := false

