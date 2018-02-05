package com.dyy.ThirdConfigutionPackage;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用装饰的设计模式
 * @auther Dyy
 * @create 2018/1/26
 */
public class SimplePropertyExcludPreFilter implements PropertyPreFilter {

    private final Set<String> excludes = new HashSet<String>();
    private final Set<String> includes = new HashSet<String>();
    /**
     * 通过装饰的设计模式来给父类设置排除的属性
     */
    public void setExcludesProperties(String ...properties)  {
        for (String item : properties) {
            if (item != null) {
                this.excludes.add(item);
            }
        }
    }
    public void setIncludesProperties(String ...properties)  {
        for (String item : properties) {
            if (item != null) {
                this.includes.add(item);
            }
        }
    }

    @Override
    public boolean apply(JSONSerializer serializer, Object source, String name) {
        if (source == null) {
            return true;
        }

        if (this.excludes.contains(name)) {
            return false;
        }

        if (includes.size() == 0 || includes.contains(name)) {
            return true;
        }
        return true;
    }

}
