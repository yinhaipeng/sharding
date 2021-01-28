package com.itheima.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 订单实体
 */
@Data
public class Order {

    private String  orderId;
    private BigDecimal price;
    private Long userId;
    private String status;

}
