package com.young.scala.sqlparser.mongo2sql.enumerator

import com.mongodb.client.MongoCollection
import com.young.scala.sqlparser.mongo2sql.converter.MongoRowConverter
import org.apache.calcite.linq4j.Enumerator
import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/3.
  */
class MongoFilterEnumerator[T](mongoCollection: MongoCollection[Document], conveter: MongoRowConverter[T]) extends Enumerator[T] with MongoEnumerator {
  override def close(): Unit = ???

  override def moveNext(): Boolean = ???

  override def current(): T = ???

  override def reset(): Unit = ???
}
