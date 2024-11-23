package com.sql.parse.druid;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableElement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;

import java.util.List;

/**
 * @author: kanedai
 * @date: 2024/11/22
 */
public class DruidTblDemo {
    public static void main(String[] args) {
        String sql = "CREATE TABLE my_db.my_table (id INT NOT NULL AUTO_INCREMENT comment '主键'," +
                "name VARCHAR(50) NOT NULL comment '名字'," +
                " age INT," +
                " primary key (`id`)) " +
                "ENGINE=InnoDB comment '测试表'";
        parseCreateTableStatement(sql);
    }

    public static void parseCreateTableStatement(String sql) {
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, "mysql");
        List<SQLStatement> statementList = parser.parseStatementList();

        for (SQLStatement statement : statementList) {
            if (statement instanceof SQLCreateTableStatement) {
                SQLCreateTableStatement createTableStatement = (SQLCreateTableStatement) statement;
                System.out.println("数据库类型: " + createTableStatement.getDbType().toString());
                SQLName name = createTableStatement.getName();
                System.out.println("库名称: " + ((SQLPropertyExpr) name).getOwnerName().toString());
                System.out.println("Table 名称: " + createTableStatement.getName().getSimpleName());
                System.out.println("Table 注解: " + createTableStatement.getComment().toString());

                List<SQLTableElement> tableElements = createTableStatement.getTableElementList();
                for (SQLTableElement element : tableElements) {
                    if (element instanceof SQLColumnDefinition) {
                        SQLColumnDefinition columnDefinition = (SQLColumnDefinition) element;
                        String columnName = columnDefinition.getName().getSimpleName();
                        System.out.println("列名 Name: " + columnName);
                        System.out.println("列类型:" + columnDefinition.getDataType().getName());
                        System.out.println("列注释:" + columnDefinition.getComment());
                        System.out.println("列是否为null:" + columnDefinition.containsNotNullConstaint());
                        System.out.println("列是否为主键:" + columnDefinition.isPrimaryKey());
                        System.out.println("===================");
                    }
                }
            }
        }
    }
}