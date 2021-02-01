package com.project.server.controller;

import com.project.abstractTree.model.Tree;
import com.project.server.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServerController {

    TreeService treeService;

    @Autowired
    public ServerController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping(value = "/tasks")
    public ResponseEntity<?> create(@RequestBody Tree<?> taskTree) {
        treeService.create(taskTree);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<Tree<?>>> read() {
        List<Tree<?>> taskTreesList = treeService.readAll();

        return taskTreesList != null && !taskTreesList.isEmpty() ?
                  new ResponseEntity<>(taskTreesList, HttpStatus.OK)
                : new ResponseEntity<>(taskTreesList, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) {
        Tree<?> taskTree = treeService.read(id);

        return taskTree != null ? new ResponseEntity<>(taskTree, HttpStatus.OK)
                : new ResponseEntity<>(taskTree, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Tree<?> taskTree) {
        boolean updated = treeService.update(taskTree, id);

        return updated ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        boolean deleted = treeService.delete(id);

        return deleted ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
