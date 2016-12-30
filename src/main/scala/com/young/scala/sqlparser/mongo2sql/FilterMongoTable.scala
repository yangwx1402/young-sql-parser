package com.young.scala.sqlparser.mongo2sql

import com.mongodb.client.MongoCollection
import org.apache.calcite.rel.`type`.RelProtoDataType
import org.bson.Document

/**
  * Created by yangyong3 on 2016/12/30.
  */
class FilterMongoTable (mongoCollection:MongoCollection[Document],relProtoDataType: RelProtoDataType) extends MongoTable(mongoCollection,relProtoDataType){

}
