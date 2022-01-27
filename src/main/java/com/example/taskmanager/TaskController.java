package com.example.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Task> taskList = taskRepository.findAllByCompletedOrderByDeadlineAsc(false);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());
        return "home";
    }

    @PostMapping(value = "/add")
    public String add(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping(value = "/complete")
    public String complete(@RequestParam Long id) {
        Task task = taskRepository.getById(id);
        task.setCompleted(true);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        List<Task> taskList = taskRepository.findAllByCompletedOrderByDeadlineAsc(true);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());
        return "archive";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        Task task = taskRepository.getById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }
}

