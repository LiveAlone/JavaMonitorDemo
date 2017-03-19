package org.yqj.monitor.demo;

import org.springframework.stereotype.Component;

/**
 * Created by yaoqijun on 2017/3/19.
 */
@Component
public class CommonService {

    public void calculateMethod(){
        int sum = 0;
        for (int i=0; i < 1000; i++){
            sum += i;
        }
    }

}
