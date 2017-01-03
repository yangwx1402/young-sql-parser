package com.young.scala.sqlparser.mongo2sql.converter.support

import com.young.scala.sqlparser.mongo2sql.converter.MongoRowConverter
import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/3.
  */
class MongoArrayConverter() extends MongoRowConverter[Array[Object]] {

  override def convertRow(document: Document): Array[Object] = document.values().toArray()
}
