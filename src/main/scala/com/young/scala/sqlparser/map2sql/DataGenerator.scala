package com.young.scala.sqlparser.map2sql

import scala.util.Random

/**
  * Created by yangyong3 on 2016/12/27.
  */
object DataGenerator {

  var data: SourceData = null

  def getSourceData(): SourceData = {
    if (data == null) {
      val studentFields = List(Column("id", "int"), Column("name", "string"), Column("age", "int"))
      val random = new Random
      val studentData = List.fill(10)(List(random.nextInt(100)+"", "yangyong", "30"))
      val studentTable = Table("student", studentFields, studentData)
      val database = DataBase(List(studentTable))
      data = SourceData(Map[String, DataBase]("database" -> database))
    }
    data
  }

  def main(args: Array[String]): Unit = {
    val sourceData = DataGenerator.getSourceData()
    sourceData.databases.map((x) => x._2.tables.map(y => y.data.map(println _)))
  }
}
