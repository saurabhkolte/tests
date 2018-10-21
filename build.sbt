
val commonSettings = Seq(
  name := "random-tests",
  version in ThisBuild := "0.1",
  scalaVersion := "2.12.7",
  organization in ThisBuild := "com.saurabhkolte.tests",
  resolvers += "Maven Central" at "http://central.maven.org/maven2/",
  resolvers += Opts.resolver.sonatypeSnapshots
)

val protoSettings = Seq(
  PB.targets in Compile := Seq(
    scalapb.gen() -> (sourceManaged in Compile).value
  )
)


lazy val root = (project in file("."))
  .aggregate(protocolbuffers)
  .settings(commonSettings)

lazy val protocolbuffers = (project in file("protocol-buffers"))
  .settings(commonSettings)
  .settings(
    libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
  )
  .settings(protoSettings)
