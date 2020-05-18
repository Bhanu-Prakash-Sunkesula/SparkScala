package Spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{lead,col}

object DisneyIoTSensorData {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.master("local").appName("DisneyIoTSensorData").getOrCreate()
    //val DisneyFilePath = args(0)
    val DisneyFilePath = "Datafiles\\DisneyIOTsensors.csv"
    val ReadDisneyIoTSensorFile = spark.read.format("csv").option("header","true").option("inferSchema","true").load(DisneyFilePath)
    val windowSpecPart = Window.orderBy("start_time")
    val DisneyIoTSensorData = ReadDisneyIoTSensorFile
          .withColumnRenamed("timestamp","start_time")
          .withColumn("data1",lead("data",1).over(windowSpecPart))
          .withColumn("end_time",lead("start_time",1).over(windowSpecPart))
      .where({col("data")===1 && col("data1")===1 || col("data")===1 && col("data1")===0  ||
         col("data")===0 && col("data1")===1 || col("data")===1 && col("data1").isNull})
      .drop("data1")
    DisneyIoTSensorData.show()
  }
}
