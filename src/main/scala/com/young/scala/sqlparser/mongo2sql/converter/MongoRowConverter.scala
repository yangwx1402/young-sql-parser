package com.young.scala.sqlparser.mongo2sql.converter

import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/3.
  */
trait MongoRowConverter[T] {
  def convertRow(document: Document): T
}
