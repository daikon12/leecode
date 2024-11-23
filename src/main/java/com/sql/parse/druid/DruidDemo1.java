package com.sql.parse.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.clickhouse.parser.ClickhouseStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.util.JdbcUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: kanedai
 * @date: 2024/11/22
 */
public class DruidDemo1 {

    public static void main(String args[]) {
        String sql = " select a.eventId as tf ,b.eventKey,eventName,flag from db.event join tt.B  as d on event.id= B.id where eventId = ? and eventKey = ? and eventName in (select a from c)";
        sql = "with metric_tb as (SELECT\n" +
                "  sys_imp_date\n" +
                "  , create_count AS count\n" +
                "FROM (\n" +
                "  SELECT\n" +
                "    sys_imp_date\n" +
                "    , create_count\n" +
                "  FROM (\n" +
                "    SELECT\n" +
                "      imp_date AS sys_imp_date\n" +
                "      , user_name\n" +
                "      , count AS create_count\n" +
                "    FROM (\n" +
                "      SELECT\n" +
                "        event,\n" +
                "        accountId AS user_name,\n" +
                "        toDate(toUInt64(serverTime) / 1000) AS imp_date,\n" +
                "        JSONExtractString(properties, 'page_title') AS page_title,\n" +
                "        JSONExtractString(properties, '$pageKey') AS page_key,\n" +
                "        accountId,\n" +
                "        department as ori_department,\n" +
                "        1 AS count,\n" +
                "        JSONExtractInt(properties, '$stayTime') / 1000 / 60 / 60 AS stay_time_hours,\n" +
                "        properties\n" +
                "      FROM\n" +
                "        dw_ods.db_event_track_table_events_all t\n" +
                "        LEFT JOIN dw_ods.user_department t2 ON t.accountId = t2.empl_name\n" +
                "      WHERE\n" +
                "        (\n" +
                "          project = 's2'\n" +
                "          OR project = 's2-bd'\n" +
                "        )\n" +
                "        AND accountId != ''\n" +
                "    ) create_src_1694\n" +
                "  ) subq_11354\n" +
                "  WHERE  (sys_imp_date >= '2023-03-26') and (sys_imp_date <= '2023-03-27') and (user_name = 'jolunoluo') \n" +
                ") subq_11357)\n" +
                "select sys_imp_date,sum( count ) AS count  from metric_tb  group by sys_imp_date   limit 2000";
        //使用mysql解析
//        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);
        ClickhouseStatementParser sqlStatementParser = new ClickhouseStatementParser(sql);
        //解析select查询
        SQLSelectStatement sqlStatement = (SQLSelectStatement) sqlStatementParser.parseSelect();
        SQLSelect sqlSelect = sqlStatement.getSelect();
        //获取sql查询块
        SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock) sqlSelect.getQuery();

        StringBuffer out = new StringBuffer();
        //创建sql解析的标准化输出
        SQLASTOutputVisitor sqlastOutputVisitor = SQLUtils.createFormatOutputVisitor(out, null, JdbcUtils.MYSQL);

        //解析select项
        out.delete(0, out.length());
        for (SQLSelectItem sqlSelectItem : sqlSelectQuery.getSelectList()) {
            if (out.length() > 1) {
                out.append(",");
            }
            sqlSelectItem.accept(sqlastOutputVisitor);
        }
        System.out.println("SELECT " + out);
        System.out.println("===========");

        //解析from
        out.delete(0, out.length());
        sqlSelectQuery.getFrom().accept(sqlastOutputVisitor);
        System.out.println("FROM:" + out);
        System.out.println("===========");

        //解析where
        out.delete(0, out.length());
        sqlSelectQuery.getWhere().accept(sqlastOutputVisitor);
        System.out.println("WHERE " + out);

        System.out.println("=================");
        getColumnsBySql(sql);
        getTableNameBySql(sql);

    }

    private static List<String> getColumnsBySql(String sql) {

        List<String> res = new ArrayList<>();
        ClickhouseStatementParser sqlStatementParser = new ClickhouseStatementParser(sql);
        SQLSelectStatement sqlStatement = (SQLSelectStatement) sqlStatementParser.parseSelect();
        SQLSelect sqlSelect = sqlStatement.getSelect();

        SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock) sqlSelect.getQuery();
        for (SQLSelectItem sqlSelectItem : sqlSelectQuery.getSelectList()) {
            String column = sqlSelectItem.toString().contains(".") ? sqlSelectItem.toString().split("\\.")[1] : sqlSelectItem.toString();
            res.add(column);
        }
        System.out.println(res);
        return res;
    }


    private static List<String> getTableNameBySql(String sql) {
//        DbType dbType = JdbcConstants.CLICKHOUSE;
//        try {
//            List<String> tableNameList = new ArrayList<>();
//            //格式化输出
//            String sqlResult = SQLUtils.format(sql, dbType);
//            List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
//            if (stmtList == null || stmtList.size() <= 0) {
//                System.out.println("stmtList为空无需获取");
//                return Collections.emptyList();
//            }
//            for (SQLStatement sqlStatement : stmtList) {
////                MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
//                ClickSchemaStatVisitor visitor = new ClickSchemaStatVisitor();
//                sqlStatement.accept(visitor);
//                Map<TableStat.Name, TableStat> tables = visitor.getTables();
//                System.out.println("druid解析sql的结果集:" + tables);
//                Set<TableStat.Name> tableNameSet = tables.keySet();
//                for (TableStat.Name name : tableNameSet) {
//                    String tableName = name.getName();
//                    if (StringUtils.isNotBlank(tableName)) {
//                        tableNameList.add(tableName);
//                    }
//                }
//            }
//            System.out.println("解析sql后的表名:" + tableNameList);
//            return tableNameList;
//        } catch (Exception e) {
//            System.out.println("**************异常SQL:[{}]*****************\\n" + sql);
//            System.out.println(e.getMessage());
//        }
        return Collections.emptyList();
    }
}