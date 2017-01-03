package com.young.java.sqlparser.mongo2sql;

import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.tree.Primitive;
import org.apache.calcite.rel.type.RelDataType;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2016/12/30.
 */
public enum MongoFieldType {

    STRING(String.class, "String"),
    BOOLEAN(Boolean.class,"Boolean"),
    BYTE(Byte.class,"Byte"),
    CHAR(java.lang.Character.class,"Character"),
    SHORT(Short.class,"Short"),
    INTEGER(Integer.class,"Integer"),
    LONG(Long.class,"Long"),
    FLOAT(Float.class,"Float"),
    DOUBLE(Double.class,"Double"),
    DATE(java.sql.Date.class, "Date"),
    TIME(java.sql.Time.class, "Time"),
    TIMESTAMP(java.sql.Timestamp.class, "Timestamp"),
    OBJECTID(ObjectId.class,"ObjectId"),
    OBJECT(Object.class,"Object");

    private final Class clazz;
    private final String simpleName;

    private static final Map<String, MongoFieldType> MAP =
            new HashMap<String, MongoFieldType>();

    static {
        for (MongoFieldType value : values()) {
            MAP.put(value.simpleName, value);
        }
    }

    MongoFieldType(Primitive primitive) {
        this(primitive.boxClass, primitive.primitiveClass.getSimpleName());
    }

    MongoFieldType(Class clazz, String simpleName) {
        this.clazz = clazz;
        this.simpleName = simpleName;
    }

    public RelDataType toType(JavaTypeFactory typeFactory) {
        return typeFactory.createJavaType(clazz);
    }

    public static MongoFieldType of(String typeString) {
        return MAP.get(typeString);
    }
}


