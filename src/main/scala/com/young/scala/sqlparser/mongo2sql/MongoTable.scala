package com.young.scala.sqlparser.mongo2sql

import com.mongodb.client.MongoCollection
import com.young.java.sqlparser.mongo2sql.MongoFieldType
import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory, RelProtoDataType}
import org.apache.calcite.schema.impl.AbstractTable
import org.bson.Document

import scala.collection.mutable.ListBuffer

/**
  * Created by yangyong3 on 2016/12/29.
  */
class MongoTable(val mongoCollection: MongoCollection[Document], var protoRowType: RelProtoDataType = null) extends AbstractTable {
  private val fieldTypes: ListBuffer[MongoFieldType] = scala.collection.mutable.ListBuffer[MongoFieldType]()

  def fetchFeilds(typeFactory: RelDataTypeFactory, fieldTypes: ListBuffer[MongoFieldType], mongoCollection: MongoCollection[Document]): RelDataType = {
    val fieldNames = new ListBuffer[String]()
    val document = mongoCollection.find().first()
    println(document)
    null
  }

  override def getRowType(typeFactory: RelDataTypeFactory): RelDataType = {
    if (protoRowType != null)
      return protoRowType.apply(typeFactory)
    if (fieldTypes.length == 0) {
      return fetchFeilds(typeFactory,fieldTypes,mongoCollection)
    }
    null
  }
}
