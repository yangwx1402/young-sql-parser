package com.young.java.sqlparser.mongo2sql;

import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.tree.Primitive;
import org.apache.calcite.rel.type.RelDataType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2016/12/30.
 */
public enum MongoFieldType {

    STRING(String.class, "string"),
    BOOLEAN(Primitive.BOOLEAN),
    BYTE(Primitive.BYTE),
    CHAR(Primitive.CHAR),
    SHORT(Primitive.SHORT),
    INT(Primitive.INT),
    LONG(Primitive.LONG),
    FLOAT(Primitive.FLOAT),
    DOUBLE(Primitive.DOUBLE),
    DATE(java.sql.Date.class, "date"),
    TIME(java.sql.Time.class, "time"),
    TIMESTAMP(java.sql.Timestamp.class, "timestamp");

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


