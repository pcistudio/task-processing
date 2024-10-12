package com.pcistudio.task.procesor.mapper;

import com.pcistudio.task.procesor.ProcessStatus;
import com.pcistudio.task.procesor.TaskInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleTaskInfoMapper implements RowMapper<TaskInfo<Object>> {
    @Override
    public TaskInfo<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TaskInfo.builder()
                .id(rs.getLong("id"))
//                .batchId(UUID.fromString(rs.getString("batch_id")))
                .status(ProcessStatus.valueOf(rs.getString("status")))
                .version(rs.getLong("version"))
                .executionTime(rs.getTimestamp("execution_time").toInstant())
                .payload(rs.getString("payload"))
                .retryCount(rs.getInt("retry_count"))
                .handlerName(rs.getString("handler_name"))
                .build();
    }
}
