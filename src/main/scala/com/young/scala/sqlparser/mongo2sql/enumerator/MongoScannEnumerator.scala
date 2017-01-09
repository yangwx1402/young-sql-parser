package com.young.scala.sqlparser.mongo2sql.enumerator

import com.mongodb.client.MongoCursor
import com.young.scala.sqlparser.mongo2sql.converter.MongoRowConverter
import org.apache.calcite.linq4j.Enumerator
import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/3.
  */
class MongoScannEnumerator[T](cursor: MongoCursor[Document],conveter: MongoRowConverter[T]) extends Enumerator[T] with MongoEnumerator {

  override def close(): Unit = {
    if (cursor != null)
      cursor.close()
  }

  override def moveNext(): Boolean = {
    cursor.hasNext
  }

  override def current(): T = {
    conveter.convertRow(cursor.next())
  }

  override def reset(): Unit = {

  }
}
