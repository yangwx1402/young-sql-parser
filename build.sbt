name := "young-scala-example"

version := "1.0"
logLevel := Level.Info

scalaVersion := "2.11.7"
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq("changyong" at "http://mvnrepository.com/artifact/",
  "Maven Repository" at "http://repo1.maven.org/maven2/",
  "maven-restlet" at "http://maven.restlet.org","Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= {
  val akka_version = "2.4.10"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akka_version,
    "com.typesafe.akka" %% "akka-remote" % akka_version,
    "com.typesafe.akka" %% "akka-cluster" % akka_version,
    "com.typesafe.akka" %% "akka-testkit" % akka_version,
    "com.typesafe.akka" %% "akka-cluster-metrics" % akka_version,
    "commons-net" % "commons-net" % "3.3",
    "com.googlecode.concurrentlinkedhashmap" % "concurrentlinkedhashmap-lru" % "1.4.2",
    "org.pentaho" % "pentaho-aggdesigner-algorithm" % "5.1.5-jhyde" from("http://conjars.org/repo/org/pentaho/pentaho-aggdesigner-algorithm/5.1.5-jhyde/pentaho-aggdesigner-algorithm-5.1.5-jhyde.jar"),
    "org.apache.calcite" % "calcite-core" % "1.9.0" exclude("org.pentaho","pentaho-aggdesigner-algorithm"),
    "org.mongodb" % "mongo-java-driver" % "3.3.0",
    "net.sf.opencsv" % "opencsv" % "2.3",
    "commons-io" % "commons-io" % "2.5",
    "junit" % "junit" % "4.12"
  )
}