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
class MongoTable(val mongoCollection: MongoCollection[Document]) extends AbstractTable {
  val fieldTypes: ListBuffer[(String, MongoFieldType)] = scala.collection.mutable.ListBuffer[(String, MongoFieldType)]()

  var protoRowType: RelProtoDataType = null

  def fetchFeilds(typeFactory: RelDataTypeFactory, mongoCollection: MongoCollection[Document]): RelDataType = {
    val document = mongoCollection.find().first()
    val names = new util.ArrayList[String]()
    val types = new util.ArrayList[RelDataType]()
    val it = document.entrySet().iterator()
    var temp: Entry[String, AnyRef] = null
    var mongoFieldType: MongoFieldType = null
    while (it.hasNext) {
      temp = it.next()
      names.add(temp.getKey)
      if (temp.getValue == null) {
        mongoFieldType = MongoFieldType.of("Object")
      } else {
        mongoFieldType = MongoFieldType.of(temp.getValue.getClass.getSimpleName)
      }
      types.add(mongoFieldType.toType((typeFactory.asInstanceOf[JavaTypeFactory])))
      fieldTypes.+=((temp.getKey, mongoFieldType))
    }
    typeFactory.createStructType(types, names)
  }

  override def getRowType(typeFactory: RelDataTypeFactory): RelDataType = {
    if (protoRowType != null)
      return protoRowType.apply(typeFactory)
    return fetchFeilds(typeFactory, mongoCollection)
  }

  def getValueObject(index: Int, value: String): Any = {
    val fieldType = fieldTypes(index)._2
    fieldType match {
      case MongoFieldType.LONG => value.toLong
      case MongoFieldType.INTEGER => value.toInt
      case MongoFieldType.STRING => value
      case MongoFieldType.BOOLEAN => value.toBoolean
      case MongoFieldType.DATE => value
      case MongoFieldType.DOUBLE => value.toDouble
    }
  }
}
