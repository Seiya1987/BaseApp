package com.seiya.net.mode;

import java.io.Serializable;

public class CacheResult<T> implements Serializable {
    private boolean isCache;
    private T cacheData;

    public CacheResult(boolean isCache, T cacheData) {
        this.isCache = isCache;
        this.cacheData = cacheData;
    }

    public boolean isCache() {
        return isCache;
    }

    public CacheResult setCache(boolean cache) {
        isCache = cache;
        return this;
    }

    public T getCacheData() {
        return cacheData;
    }

    public CacheResult setCacheData(T cacheData) {
        this.cacheData = cacheData;
        return this;
    }

    @Override
    public String toString() {
        return "CacheResult{" +
                "isCache=" + isCache +
                ", cacheData=" + cacheData +
                '}';
    }
}
