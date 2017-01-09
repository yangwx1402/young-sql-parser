package com.young.scala.sqlparser.mongo2sql.table

/**
  * Created by young.yang on 2016/12/29.
  * 表类型
  */
sealed trait TableType

case object SCAN extends TableType

case object FILTER extends TableType

case object TRANSLATABLE extends TableType

object TableType {
  def of(typeString: String): TableType = {
    if (SCAN.toString.equals(typeString)) {
      return SCAN
    } else if (FILTER.toString.equals(typeString)) {
      return FILTER
    } else if (TRANSLATABLE.toString.equals(typeString)) {
      return TRANSLATABLE
    } else {
      return SCAN
    }
  }
}