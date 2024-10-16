package com.pcistudio.task.procesor;

import com.pcistudio.task.procesor.page.Cursor;
import com.pcistudio.task.procesor.page.DefaultCursorPageableFactory;
import com.pcistudio.task.procesor.task.TaskInfo;

import java.time.Instant;

public class TaskInfoCursorPageableFactory extends DefaultCursorPageableFactory<TaskInfo, Instant> {
    @Override
    protected Cursor<Instant> createCursor(TaskInfo lasTaskInfo) {
        return new Cursor<>(lasTaskInfo.getId(), lasTaskInfo.getExecutionTime());
    }
}