package com.seiya.net.strategy;

import com.seiya.net.core.ApiCache;
import com.seiya.net.mode.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;

public interface ICacheStrategy<T> {
    <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Type type);
}
