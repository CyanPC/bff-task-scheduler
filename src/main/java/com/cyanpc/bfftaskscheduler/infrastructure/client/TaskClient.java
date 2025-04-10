package com.cyanpc.bfftaskscheduler.infrastructure.client;
import com.cyanpc.bfftaskscheduler.business.dto.TaskDTO;
import com.cyanpc.bfftaskscheduler.infrastructure.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler", url = "${task.url}")
public interface TaskClient {

    @PostMapping
    TaskDTO setTask(@RequestBody TaskDTO dto,
                    @RequestHeader("Authorization") String token));

    @GetMapping("/events")
   List<TaskDTO> findTaskListByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInitial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
   List<TaskDTO> findTaskByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskByID(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTO changeNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                     @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTO updateTask(@RequestBody TaskDTO dto,
                       @RequestParam("id") String id,
                       @RequestHeader("Authorization") String token);

}
