package com.project.server.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.project.abstractTree.model.Tree;

import java.util.concurrent.atomic.AtomicInteger;

public final class Constants {
    // Хранилище задач
    public static final Cache<Integer, Tree<?>> CACHE = CacheBuilder.newBuilder().build();

    // Переменная для генерирования ID задачи
    public static final AtomicInteger TASKTREE_ID_HOLDER = new AtomicInteger();
}
