package com.cyanpc.bfftaskscheduler.controller;

import com.cyanpc.task_scheduler.business.TaskService;
import com.cyanpc.task_scheduler.business.dto.TaskDTO;
import com.cyanpc.task_scheduler.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> setTask(@RequestBody TaskDTO dto,
                                           @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(taskService.setTask(token, dto));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TaskDTO>> findTaskListByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInitial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFinal) {

        return ResponseEntity.ok(taskService.findScheduledTasksByPeriod(dateInitial, dateFinal));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findTaskByEmail(@RequestHeader("Authorization") String token) {
        List<TaskDTO> tasks = taskService.findTaskByEmail(token);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskByID(@RequestParam("id") String id) {
        taskService.deleteTaskByID(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TaskDTO> changeNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                                            @RequestParam("id") String id) {
        return ResponseEntity.ok(taskService.changeStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO dto,
                                              @RequestParam("id") String id) {
        return ResponseEntity.ok(taskService.updateTask(dto, id));
    }
}