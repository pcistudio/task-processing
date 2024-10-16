package com.pcistudio.task.procesor;

import com.pcistudio.task.procesor.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class LoggingJdbcTemplate extends JdbcTemplate {

    public LoggingJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int update(String sql, PreparedStatementSetter pss) {
        int update = super.update(sql, pss);
        logSqlAndParameters(sql, pss);
        return update;
    }

    @Override
    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        List<T> query = super.query(sql, pss, rowMapper);
        logSqlAndParameters(sql, pss);
        return query;
    }

    @Override
    @Nullable
    public <T> T query(String sql, @Nullable PreparedStatementSetter pss, ResultSetExtractor<T> rse) throws DataAccessException {
        T query = super.query(sql, pss, rse);
        logSqlAndParameters(sql, pss);
        return query;
    }

    private void logSqlAndParameters(String sql, PreparedStatementSetter pss) {
        if (!log.isDebugEnabled()) {
            return;
        }
//        log.debug("SQL: " + sql);
        if (pss instanceof ArgumentPreparedStatementSetter) {

            Field argsFields = ReflectionUtils.findField(ArgumentPreparedStatementSetter.class, "args");
            ReflectionUtils.makeAccessible(argsFields);
            Object[] args = (Object[]) ReflectionUtils.getField(argsFields, pss);
            log.debug("Parameters: ");
            for (Object arg : args) {
                Assert.notNull(arg, "Argument must not be null");
                log.debug(arg.toString());
            }
        }
        log.debug("---------------------------------------------");
    }
}