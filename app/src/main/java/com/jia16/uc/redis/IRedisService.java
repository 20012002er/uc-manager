package com.jia16.uc.redis;

import java.util.List;

/**
 * Created by lazyb on 2017/7/7.
 */
public interface IRedisService {

    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key,long expire);

    <T> boolean setList(String key ,List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lpush(String key,Object obj);

    long rpush(String key,Object obj);

    String lpop(String key);

}
