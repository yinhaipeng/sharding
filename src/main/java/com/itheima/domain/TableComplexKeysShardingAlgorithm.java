package com.itheima.domain;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import java.util.Collection;

public class TableComplexKeysShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String value = preciseShardingValue.getValue().substring(preciseShardingValue.getValue().length() - 2, preciseShardingValue.getValue().length());
        String prex = "";
        if(value.equals("FH")){
            prex="0";
        }else{
            prex="1";
        }
        for (String name : collection) {
            String nameSuffix = name.substring(name.length() - 1, name.length());
            if (nameSuffix.equals(prex)) {
                return name;
            }
        }
        return null;
    }

}