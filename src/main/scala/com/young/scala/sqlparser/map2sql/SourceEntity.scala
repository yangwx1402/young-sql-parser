package com.young.scala.sqlparser.map2sql

/**
  * Created by yangyong3 on 2016/12/27.
  */
case class DataBase(tables: List[Table]) extends Serializable

case class Table(tableName: String, fileds: List[Column], data: List[List[String]]) extends Serializable

case class Column(name: String, datatype: String) extends Serializable

case class SourceData(databases: Map[String, DataBase])

object Constant {
//  val sqlTypeMapCalciteType: Map[String, SqlTypeName] = Map[String, SqlTypeName](
//    "varchar" -> SqlTypeName.VARCHAR, "int" -> SqlTypeName.INTEGER
//  )
//  val sqlTypeMapJavaType: Map[String, Class] = Map[String, Class](
//    "varchar" -> classOf[String].asInstanceOf[Class], "int" -> classOf[Int].asInstanceOf[Class]
//  )
}


