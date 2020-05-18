name := "SparkScalaExer"
version := "0.1"
scalaVersion := "2.11.8"

val sparkVer = "2.4.0"
val mongoDBVersion = "3.4.3"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer,
    "org.apache.spark" %% "spark-sql" % sparkVer ,
    "org.apache.spark" %% "spark-hive" % sparkVer,
    "org.apache.spark" %% "spark-graphx" % sparkVer,
    "org.apache.spark" %% "spark-mllib" % sparkVer
  )
}
libraryDependencies ++= {
  Seq(
    "org.mongodb.spark" %% "mongo-spark-connector" % sparkVer,
    "org.mongodb" % "mongo-java-driver" % mongoDBVersion,
    "org.mongodb" % "mongodb-driver" % mongoDBVersion,
    "com.typesafe" % "config" % "1.4.0",
    "org.apache.commons" % "commons-text" % "1.8"
  )
}