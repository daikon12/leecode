package com.sql.parse.druid;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectOrderByItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;

import java.util.List;

/**
 * @author: kanedai
 * @date: 2024/11/22
 */
public class DruidSelectDemo {
    public static void main(String[] args) {
        String sql = "SELECT t1.id, t1.name, t2.salary\n" +
                "FROM table1 t1\n" +
                "JOIN table2 t2 ON t1.id = t2.id\n" +
                "WHERE t1.age > 25 and t2.id != 4 or t1 < 4\n" +
                "GROUP BY t1.id\n" +
                "HAVING COUNT(t2.salary) > 100\n" +
                "ORDER BY t1.name DESC;";
        parseSelectStatement(sql);

    }

    public static void parseSelectStatement(String sql) {
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, "mysql");
        SQLStatement statement = parser.parseStatement();

        if (statement instanceof SQLSelectStatement) {
            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statement;
            SQLSelect select = sqlSelectStatement.getSelect();
            SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) select.getQuery();

            // 解析表名
            SQLTableSource from = queryBlock.getFrom();
            if (from instanceof SQLJoinTableSource) {
                SQLJoinTableSource join = (SQLJoinTableSource) from;
                System.out.println("Table Name: " + join);
                System.out.println("left: " + join.getLeft() + ", alias:" + join.getLeft().getAlias());
                SQLTableSource right = join.getRight();
                if (right instanceof SQLExprTableSource) {
                    SQLExprTableSource rightSpource = (SQLExprTableSource) right;
                    System.out.println("right Table Name: " + rightSpource.getExpr().toString());
                }
            }

            // 解析查询字段
            List<SQLSelectItem> selectItems = queryBlock.getSelectList();
            for (SQLSelectItem selectItem : selectItems) {
                SQLExpr expr = selectItem.getExpr();
                if (expr instanceof SQLIdentifierExpr) {
                    SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) expr;
                    System.out.println("Select Field: " + identifierExpr.getName());
                } else if (expr instanceof SQLPropertyExpr) {
                    SQLPropertyExpr propertyExpr = (SQLPropertyExpr) expr;
                    System.out.println("Select Field: " + propertyExpr.getName());
                }
            }

            // 解析过滤字段
            SQLExpr where = queryBlock.getWhere();
            if (where instanceof SQLBinaryOpExpr) {
                SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) where;
                System.out.println("======= parseBinaryOpExpr =======");
                parseBinaryOpExpr(binaryOpExpr);
            }

            // 解析排序字段
            List<SQLSelectOrderByItem> orderByItems = queryBlock.getOrderBy().getItems();
            ;
            for (SQLSelectOrderByItem orderByItem : orderByItems) {
                SQLExpr orderByExpr = orderByItem.getExpr();
                if (orderByExpr instanceof SQLIdentifierExpr) {
                    SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) orderByExpr;
                    System.out.println("Order By Field: " + identifierExpr.getName());
                } else if (orderByExpr instanceof SQLPropertyExpr) {
                    SQLPropertyExpr propertyExpr = (SQLPropertyExpr) orderByExpr;
                    System.out.println("Order By Field: " + propertyExpr.getName());
                }
            }

            // 解析聚合字段
            List<SQLExpr> groupByItems = queryBlock.getGroupBy().getItems();
            for (SQLExpr groupByExpr : groupByItems) {
                if (groupByExpr instanceof SQLAggregateExpr) {
                    SQLAggregateExpr aggregateExpr = (SQLAggregateExpr) groupByExpr;
                    System.out.println("Aggregate Field: " + aggregateExpr.getMethodName());
                } else if (groupByExpr instanceof SQLIdentifierExpr) {
                    SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) groupByExpr;
                    System.out.println("Group By Field: " + identifierExpr.getName());
                } else if (groupByExpr instanceof SQLPropertyExpr) {
                    SQLPropertyExpr propertyExpr = (SQLPropertyExpr) groupByExpr;
                    System.out.println("Group By Field: " + propertyExpr.getName());
                }
            }
        }
    }

    private static void parseBinaryOpExpr(SQLBinaryOpExpr binaryOpExpr) {
        SQLExpr left = binaryOpExpr.getLeft();
        SQLExpr right = binaryOpExpr.getRight();
        SQLBinaryOperator operator = binaryOpExpr.getOperator();

        if (left instanceof SQLIdentifierExpr) {
            SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) left;
            SQLIntegerExpr integerExpr = (SQLIntegerExpr) right;

            String columnName = identifierExpr.getName();
            String operatorSymbol = operator.getName();
            int compareValue = integerExpr.getNumber().intValue();

            System.out.println("Column Name: " + columnName);
            System.out.println("Operator: " + operatorSymbol);
            System.out.println("Compare Value: " + compareValue);
        }

        if(left instanceof SQLPropertyExpr) {
            SQLPropertyExpr propertyExpr = (SQLPropertyExpr) left;
            String columnName = propertyExpr.getName();
            String operatorSymbol = operator.getName();
            System.out.println("Column Name: " + columnName);
            System.out.println("Operator: " + operatorSymbol);
            System.out.println("Compare Value: " + right);
        }


        if (binaryOpExpr.getLeft() instanceof SQLBinaryOpExpr) {
            parseBinaryOpExpr((SQLBinaryOpExpr) binaryOpExpr.getLeft());
        }

        if (binaryOpExpr.getRight() instanceof SQLBinaryOpExpr) {
            parseBinaryOpExpr((SQLBinaryOpExpr) binaryOpExpr.getRight());
        }
    }
}