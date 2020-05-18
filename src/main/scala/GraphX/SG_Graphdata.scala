package GraphX
import org.apache.spark.sql.SparkSession
import org.graphframes._
import org.apache.spark.graphx._

object SG_Graphdata {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("GraphQuery").master("local").getOrCreate()
    val v = spark.createDataFrame(List(
      (1,"Societe Generale"),
      (2,"Credit Agricole"),
      (3,"UBS"),
      (4,"HSBC"),
      (5,"BNP Paribas"),
      (6,"RBS"),
      (7,"Santander"),
      (8,"Boursorama"),
      (9,"Deutsche")
    )).toDF("id","Company")
    val e = spark.createDataFrame(List(
      (1,2,"parent"),
      (1,3,"parent"),
      (2,4,"parent"),
      (2,5,"parent"),
      (2,8,"parent"),
      (3,6,"parent"),
      (4,7,"parent"),
      (5,8,"parent"),
      (6,9,"parent"))
    ).toDF("src","dst","relationship")
    import spark.implicits._
    val g = GraphFrame(v,e)
    val graphData = g.toGraphX.triplets
    //yet to design the code ...soon....



  }

}
