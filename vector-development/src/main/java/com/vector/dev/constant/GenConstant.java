package com.vector.dev.constant;

/**
 * @author wengxs
 */
public interface GenConstant {

    String JAVA_TYPE_STRING = "String";

    String JAVA_TYPE_INTEGER = "Integer";

    String JAVA_TYPE_LONG = "Long";

    String JAVA_TYPE_DOUBLE = "Double";

    String JAVA_TYPE_BIG_DECIMAL = "BigDecimal";

    String JAVA_TYPE_DATE = "Date";

    String JAVA_TYPE_BOOLEAN = "Boolean";

    String JAVA_TYPE_JSON = "JSONArray";

    String[] FIELD_TYPE_STRING = { "char", "varchar", "nvarchar", "varchar2", "tinytext", "text",
            "mediumtext", "longtext" };

    String[] FIELD_TYPE_TIME = { "datetime", "time", "date", "timestamp" };

    String[] FIELD_TYPE_NUMBER = { "smallint", "mediumint", "int", "integer",
            "bigint", "float", "double", "decimal" };

    String[] FIELD_TYPE_LONG = { "bigint" };

    String[] FIELD_TYPE_INTEGER = { "smallint", "mediumint", "int", "integer" };

    String[] FIELD_TYPE_DECIMAL = { "decimal" };

    String[] FIELD_TYPE_DOUBLE = { "float", "double"};

    String[] FIELD_TYPE_BOOLEAN = { "tinyint", "bit" };

    String[] FIELD_TYPE_JSON = { "json" };

    String DEFAULT_PACKAGE_PREFIX = "com.vector.";
}
