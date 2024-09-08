package com.caizhidao.task;

import com.caizhidao.entity.Orders;
import com.caizhidao.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务类，定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ?")//每分钟触发一次
    public void processTimeoutOrder(){
        log.info("定时处理超时未支付订单：{}", LocalDateTime.now());

        //select * from orders where status = ? and order_time < (当前时间 - 15分钟)
        //查询未支付状态订单，且下单时间超过15分钟，即超时未支付订单
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if (ordersList !=null && ordersList.size()>0){
            ordersList.forEach(order -> {
               order.setStatus(Orders.CANCELLED);
               order.setCancelReason("订单支付超时，自动取消");
               order.setCancelTime(LocalDateTime.now());
               orderMapper.update(order);
            });
        }
    }

    /**
     * 处理一直处于派送中订单
     */
    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨一点触发一次
    public void processDeliveryOrder(){
        log.info("定时处理派送中订单{}", LocalDateTime.now());

        //查询派送中订单，且下单时间为前一天（当前时间-1小时）
        LocalDateTime time = LocalDateTime.now().plusHours(-1);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList !=null && ordersList.size()>0){
            ordersList.forEach(order -> {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            });
        }
    }
}
