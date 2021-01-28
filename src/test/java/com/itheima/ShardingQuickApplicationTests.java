package com.itheima;

import com.itheima.domain.Order;
import com.itheima.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingQuickApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    //--------------水平分表
    //测试新增
    @Test
    public void testInsertOrder() {

        Order order = new Order();
        order.setPrice(new BigDecimal((1 + 1) * 5));
        order.setStatus("success" + "|" + 1);
        order.setUserId(1L);
        order.setOrderId("20201215FH");
        orderMapper.inserOrder(order);


        Order order2 = new Order();
        order2.setPrice(new BigDecimal((1 + 1) * 5));
        order2.setStatus("success" + "|" + 2);
        order2.setUserId(2L);
        order2.setOrderId("20201214TR");
        orderMapper.inserOrder(order2);
    }

    //根据id查询多个订单
    @Test
    public void testSelect() {
//        List<Long> orderIds=new ArrayList<>();
//        orderIds.add(491708667008122880L);
//        orderIds.add(491708666341228545L);
        List<Map> list = orderMapper.selectListByOrderIds("20201215FH");

        System.out.println(list);

    }

    @Test
    public void testOrder(){
        List<String> orders=orderMapper.selectByUserId(1);

        List<Map> all=new ArrayList<>();
        for (String orderId: orders) {
            List<Map> list = orderMapper.selectListByOrderIds(orderId);
            all.addAll(list);
        }
        System.out.println(all);
    }

}
