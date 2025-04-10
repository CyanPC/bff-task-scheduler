package com.cyanpc.bfftaskscheduler.business;

import com.cyanpc.bfftaskscheduler.business.dto.TaskDTO;
import com.cyanpc.bfftaskscheduler.infrastructure.client.TaskClient;
import com.cyanpc.bfftaskscheduler.infrastructure.enums.NotificationStatusEnum;
import com.cyanpc.task_scheduler.exceptions.ResourceNotFoundException;
import com.cyanpc.task_scheduler.infrastructure.entity.TaskEntity;
import com.cyanpc.task_scheduler.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;
    public TaskDTO setTask(String token, TaskDTO dto) {
        return taskClient.setTask(dto, token);

    }

    public List<TaskDTO> findTaskListByPeriod(LocalDateTime dateInitial,
                                              LocalDateTime dateFinal,
                                              String token) {
        return taskClient.findTaskListByPeriod(dateInitial, dateFinal, token);
    }

    public List<TaskDTO> findTaskByEmail(String token) {
        return taskClient.findTaskByEmail(token);
    }

    public void deleteTaskByID(String id, String token) {
        taskClient.deleteTaskByID(id, token);
    }

    public TaskDTO changeNotificationStatus(NotificationStatusEnum status, String id, String token) {
    return taskClient.changeNotificationStatus(status, id, token);
    }

    public TaskDTO updateTask(TaskDTO dto, String id, String token){
        return taskClient.updateTask(dto, id, token);
    }
}
