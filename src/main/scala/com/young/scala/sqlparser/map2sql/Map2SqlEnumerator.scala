package com.young.scala.sqlparser.map2sql

import org.apache.calcite.linq4j.Enumerator

/**
  * Created by yangyong3 on 2016/12/27.
  */
class Map2SqlEnumerator(tableData: List[List[String]], fields: List[Column]) extends Enumerator[Array[Object]] {

  private var index = 0

  override def close(): Unit = {
    println("close()")
  }

  override def moveNext(): Boolean = {
    tableData.size > (index)
  }

  override def current(): Array[Object] = {
    val rs = tableData(index)
    index += 1
    rs.toArray
  }

  override def reset(): Unit = {
    index = 0
  }
}
