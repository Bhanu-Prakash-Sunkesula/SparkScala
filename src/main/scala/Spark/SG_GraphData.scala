package Spark

import org.apache.spark.sql.SparkSession

object SG_GraphData {
  def main(args: Array[String]): Unit = {
  val spark = SparkSession.builder.master("local").appName("Graph_data").getOrCreate()
  //val graphdataFilepath = args(0)
  val graphdataFilepath = "Datafiles\\GraphDatafile.csv"
  val graphdata = spark.read.format("csv").option("header","true").option("inferSchema","true").load(graphdataFilepath)
  graphdata.createOrReplaceTempView("graph")
  val graph = spark.sql("select a.Node_id as Company_id,a.Node_Name as Company_Name,b.Node_Name as Child_Company_Name " +
    "from graph a join graph b on a.Node_id = b.Edge_id order by a.Node_id")
  graph.show()
  }
}
