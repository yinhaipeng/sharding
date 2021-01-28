package com.itheima.mapper;

import com.itheima.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 订单mapper接口
 */
@Mapper
public interface OrderMapper {
    //新增
    @Insert("insert into t_order(price,user_id,status,order_id) values(#{price},#{userId},#{status},#{orderId})")
     int inserOrder(Order order);
    //根据id查询多个订单
    @Select("select i.* from t_order t left join item i on t.order_id=i.order_id  where t.order_id in(#{orderIds})")
     List<Map> selectListByOrderIds(@Param("orderIds") String orderIds);


    @Select("select uo.order_id from user_order uo where uo.user_id=#{userId} limit 1,1")
    List<String> selectByUserId(@Param("userId") int userId);
}
