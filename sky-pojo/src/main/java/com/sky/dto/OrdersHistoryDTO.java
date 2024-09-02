package com.sky.dto;

import lombok.Data;

@Data
public class OrdersHistoryDTO {

    private int page;

    private int pageSize;

    //订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
    private Integer status;
}
