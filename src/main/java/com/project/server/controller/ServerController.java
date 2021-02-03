package com.project.server.controller;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Tree;
import com.project.server.model.Task;
import com.project.server.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerController {

    TreeService treeService;

    @Autowired
    public ServerController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping(value = "/tasks/{id}")
    public ResponseEntity<Tree<Task>> create(@PathVariable(name = "id") int id, @RequestBody String name) {
        Tree<Task> taskTree =  treeService.create(id, name);

        return taskTree != null ?
                new ResponseEntity<>(taskTree, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<Tree<Task>> read() {
        Tree<Task> taskTree = treeService.readAll();

        return taskTree != null && taskTree.getRoot() != null ?
                new ResponseEntity<>(taskTree, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<Node<Task>> read(@PathVariable(name = "id") int id) {
        Node<Task> taskNode = treeService.read(id);

        return taskNode != null ? new ResponseEntity<>(taskNode, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Task task) {
        boolean updated = treeService.update(id, task);

        return updated ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/tasks/{id}?type={keyword}")
    public ResponseEntity<Node<Task>> delete(@PathVariable(name = "id") int id, @PathVariable (name = "keyword") String type) {
        Node<Task> parentNode = treeService.delete(id, Boolean.parseBoolean(type));

        return parentNode != null ?
                new ResponseEntity<>(parentNode, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
