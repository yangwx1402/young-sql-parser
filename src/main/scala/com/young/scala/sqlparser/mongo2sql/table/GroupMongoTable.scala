package com.young.scala.sqlparser.mongo2sql.table

import com.mongodb.client.MongoCollection
import org.apache.calcite.rel.`type`.RelProtoDataType
import org.bson.Document

/**
  * Created by yangyong3 on 2016/12/30.
  */
class GroupMongoTable(mongoCollection:MongoCollection[Document]) extends MongoTable(mongoCollection){

}
