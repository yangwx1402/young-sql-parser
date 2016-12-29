package com.young.scala.sqlparser.map2sql

import java.util

import com.google.common.collect.{ImmutableMultimap, Multimap}
import org.apache.calcite.schema.impl.AbstractSchema

/**
  * Created by yangyong3 on 2016/12/27.
  */
class Map2SqlSchema(databaseName: String) extends AbstractSchema {
  /**
    * 这个接口是为了检查cache是否过期，因为calcite默认会缓存schema的元数据，所以可以通过该函数实现cache有效性检查。
    *
    * @param lastCheck
    * @param now
    * @return
    */
  override def contentsHaveChangedSince(lastCheck: Long, now: Long): Boolean = super.contentsHaveChangedSince(lastCheck, now)

  /**
    * 这个接口是为了获取schema的元数据，返回值为表名和表对象的映射。
    *
    * @return
    */
  override def getTableMap(): java.util.Map[String, org.apache.calcite.schema.Table] = {
    val sourceData = DataGenerator.getSourceData()
    val database = sourceData.databases(databaseName)
    val result = new util.HashMap[String, org.apache.calcite.schema.Table]()
    database.tables.foreach(table => result.put(table.tableName, new Map2SqlTable(table)))
    result
  }

  /**
    * 这个接口为了获取该schema支持的UDF函数。
    *
    * @return
    */
  override def getFunctionMultimap(): Multimap[String, org.apache.calcite.schema.Function] = ImmutableMultimap.of()
}
