package org.dev.mybatis.generator;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * Created by cnlym on 2017/3/14.
 */
public class CreateUserAndModifyUserPlugin extends PluginAdapter {


    @Override
    public boolean validate(List<String> warnings) {
        return false;
    }
}
