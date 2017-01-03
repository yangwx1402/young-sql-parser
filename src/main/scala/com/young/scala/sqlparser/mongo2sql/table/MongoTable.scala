package com.young.scala.sqlparser.mongo2sql.table

import java.util
import java.util.Map.Entry

import com.mongodb.client.MongoCollection
import com.young.java.sqlparser.mongo2sql.MongoFieldType
import org.apache.calcite.adapter.java.JavaTypeFactory
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
    val document = mongoCollection.find().first()
    val names = new util.ArrayList[String]()
    val types = new util.ArrayList[RelDataType]()
    val it = document.entrySet().iterator()
    var temp: Entry[String, AnyRef] = null
    while (it.hasNext) {
      temp = it.next()
      names.add(temp.getKey)
      if (temp.getValue == null) {
        types.add(MongoFieldType.of("Object").toType((typeFactory.asInstanceOf[JavaTypeFactory])))
      } else {
        types.add(MongoFieldType.of(temp.getValue.getClass.getSimpleName).toType((typeFactory.asInstanceOf[JavaTypeFactory])))
      }
    }
    typeFactory.createStructType(types, names)
  }

  override def getRowType(typeFactory: RelDataTypeFactory): RelDataType = {
    if (protoRowType != null)
      return protoRowType.apply(typeFactory)
    return fetchFeilds(typeFactory, fieldTypes, mongoCollection)
  }
}
