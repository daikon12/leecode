package com.sql.parse.jsql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectVisitorAdapter;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author: kanedai
 * @date: 2024/11/22
 */
public class JsqlSelectDemo {
    public static void main(String[] args) throws JSQLParserException {
        String sql = "SELECT t1.id as id, t1.name, t2.salary\n" +
                "FROM table1 t1\n" +
                "JOIN table2 t2 ON t1.id = t2.id\n" +
                "WHERE t1.age > 25 and t2.id != 4 or t1 < 4\n" +
                "GROUP BY t1.id\n" +
                "HAVING COUNT(t2.salary) > 100\n" +
                "ORDER BY t1.name DESC limit 10;";
//        parseSelectStatement(sql);

        String demoSql = "SELECT DISTINCT u.id, r.role_name, u.user_name, u.sex, u.email " +
                "            FROM t_user u " +
                "            LEFT JOIN t_role r ON u.role_id = r.id " +
                "            WHERE r.role_name = '管理员' and id = 4 " +
                "            ORDER BY u.age DESC " +
                "            LIMIT 0,10";
//        parseSql(demoSql);

         String insertSql = "INSERT INTO t_user (role_id, user_name, email, age, sex, register_time )\n" +
                "VALUES ( 1, 'xw', 'isxuwei@qq.com', 25, '男', '2024-04-12 17:37:18' );";
//          parseInsertSql(insertSql);

        String updateSql = "UPDATE t_user SET email = '373675032@qq.com', phone = '10086' WHERE id = 1";
        parseUpdateSql(updateSql);
    }

    private static void parseUpdateSql(String updateSql) throws JSQLParserException {
        Update update = (Update) CCJSqlParserUtil.parse(updateSql);
        System.out.println("【更新目标表】：" + update.getTable());
        List<UpdateSet> updateSets = update.getUpdateSets();
        for (UpdateSet updateSet : updateSets) {
            System.out.println("【更新字段】：" + updateSet.getColumns());
            System.out.println("【更新字】：" + updateSet.getValues());
        }
        System.out.println("【更新条件】：" + update.getWhere());
        System.out.println("--------------------------------------------------------");
        // 去掉更新手机号
        updateSets.remove(1);
        // 添加更新字段
        UpdateSet updateSet = new UpdateSet();
        updateSet.add(new Column("update_time"), new LongValue(System.currentTimeMillis()));
        updateSets.add(updateSet);
        // 更新 Where 条件
        AndExpression expression = new AndExpression();
        expression.withLeftExpression(update.getWhere());
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("deleted"));
        equalsTo.setRightExpression(new LongValue(0));
        expression.withRightExpression(equalsTo);
        update.setWhere(expression);
        System.out.println("【处理后 SQL】" + update);
    }

    private static void parseInsertSql(String SQL) throws JSQLParserException {
        Insert insert = (Insert) CCJSqlParserUtil.parse(SQL);
        System.out.println("【插入目标表】：" + insert.getTable());
        System.out.println("【插入字段】：" + insert.getColumns());
        System.out.println("【插入值】：" + insert.getValues());
        System.out.println("--------------------------------------------------------");
        ExpressionList<Column> columns = insert.getColumns();
        ExpressionList<Expression> values = (ExpressionList<Expression>) insert.getValues().getExpressions();
        // 字段和值是一一对应的，把性别删除掉
        columns.remove(4);
        values.remove(4);
        // 新增一列状态，默认为 create
        columns.add(new Column("status"));
        values.add(new StringValue("create"));
        // 更新年龄字段 +1
        Expression expression = values.get(3);
        LongValue longValue = (LongValue) expression;
        longValue.setValue(longValue.getValue() + 1);
        System.out.println("【处理后 SQL】" + insert);
    }

    private static void parseSql(String sql) throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect plainSelect = select.getPlainSelect();
        System.out.println("【DISTINCT 子句】：" + plainSelect.getDistinct());
        System.out.println("【查询字段】：" + plainSelect.getSelectItems());
        System.out.println("【FROM 表】：" + plainSelect.getFromItem());
        System.out.println("【WHERE 子句】：" + plainSelect.getWhere());
        System.out.println("【JOIN 子句】：" + plainSelect.getJoins());
        System.out.println("【LIMIT 子句】：" + plainSelect.getLimit());
        System.out.println("【OFFSET 子句】：" + plainSelect.getOffset());
        System.out.println("【ORDER BY 子句】：" + plainSelect.getOrderByElements());
        System.out.println("--------------------------------------------------------");
        // 取消去重
        plainSelect.setDistinct(null);
        // 修改查询字段为 *
        List<SelectItem<?>> selectItems = new ArrayList<>();
        selectItems.add(new SelectItem<>(new AllColumns()));
        plainSelect.setSelectItems(selectItems);
        // 修改 WHERE 子句
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("u.id"));
        equalsTo.setRightExpression(new LongValue(1));
        plainSelect.setWhere(equalsTo);
        // 修改 LIMIT 子句
        Limit limit = new Limit();
        limit.setRowCount(new LongValue(5));
        limit.setOffset(new LongValue(0));
        plainSelect.setLimit(limit);
        // 修改排序为 u.age ASC
        OrderByElement orderByElement = new OrderByElement();
        orderByElement.setExpression(new Column("u.age"));
        orderByElement.setAsc(true); // 升序
        plainSelect.setOrderByElements(Collections.singletonList(orderByElement));
        System.out.println("【处理后 SQL】" + plainSelect);
    }

    private static void parseSelectStatement(String sql) throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        Set<String> tables = tablesNamesFinder.getTables((Statement) select);
        System.out.println("tables:" + tables);

        PlainSelect plainSelect = select.getPlainSelect();
        List<SelectItem<?>> selectItems = plainSelect.getSelectItems();
        for(SelectItem item : selectItems) {
            System.out.println(item.getExpression().toString() + ", alias :" + item.getAlias());
        }

        

    }
}