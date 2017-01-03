package com.young.scala.sqlparser.mongo2sql.example

import java.sql.{Connection, DriverManager}
import java.util.Properties

/**
  * Created by yangyong3 on 2017/1/3.
  */
object Mongo2sqlExample {

  def main(args: Array[String]) {
    val model: String = "D:\\young\\scala\\young-scala-example\\src\\main\\resources\\mongo2sql.json"
    Class.forName("org.apache.calcite.jdbc.Driver")
    val connection: Connection = DriverManager.getConnection("jdbc:calcite:model=" + model, new Properties)
    val stmt = connection.createStatement()
    val sql = "select * from \"beidou_yuqing_mdata\" where \"beidou_id\" = 291701001 order by \"insert_time_long\" asc"
    val rs = stmt.executeQuery(sql)
    println(sql)
    val index = rs.getMetaData.getColumnCount
    while(rs.next()){
      for(i<-1 to index){
        print(rs.getObject(i)+",")
      }
      println
    }
  }
}
