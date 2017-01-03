package com.young.scala.sqlparser.mongo2sql.enumerator

import com.mongodb.client.{MongoCollection, MongoCursor}
import org.bson.Document

/**
  * Created by yangyong3 on 2017/1/3.
  */
trait MongoEnumerator {

  var cursor: MongoCursor[Document] = null

  def find(mongoCollection: MongoCollection[Document]): Unit = {
    if (cursor == null) {
      val find = new Document()
      cursor = mongoCollection.find(find).iterator()
    }
  }
}
