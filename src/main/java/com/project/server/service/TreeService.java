package com.project.server.service;

import com.project.abstractTree.model.Tree;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.server.service.Constants.CACHE;
import static com.project.server.service.Constants.TASKTREE_ID_HOLDER;

@Service
public class TreeService {

    public void create(Tree<?> taskTree) {
        final int taskTreeId = TASKTREE_ID_HOLDER.incrementAndGet();
        taskTree.getRoot().setId(taskTreeId);
        CACHE.put(taskTreeId, taskTree);
    }

    public Tree<?> read(int id) {
        return CACHE.getIfPresent(id);
    }

    public List<Tree<?>> readAll() {

        return new ArrayList<>(CACHE.asMap().values());
    }


    public boolean update(Tree<?> taskTree, int id) {
        if (CACHE.asMap().containsKey(id)) {
            taskTree.getRoot().setId(id);
            CACHE.put(id, taskTree);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        CACHE.invalidate(id);
        return CACHE.getIfPresent(id) == null;
    }
}
