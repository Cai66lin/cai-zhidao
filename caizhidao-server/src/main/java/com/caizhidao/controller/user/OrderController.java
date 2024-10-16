package com.caizhidao.controller.user;

import com.caizhidao.dto.OrdersHistoryDTO;
import com.caizhidao.dto.OrdersPaymentDTO;
import com.caizhidao.dto.OrdersSubmitDTO;
import com.caizhidao.result.PageResult;
import com.caizhidao.result.Result;
import com.caizhidao.service.OrderService;
import com.caizhidao.vo.OrderPaymentVO;
import com.caizhidao.vo.OrderSubmitVO;
import com.caizhidao.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户端订单相关接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @ApiOperation("用户下单")
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("用户下单，参数为：{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     * @throws Exception
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }

    /**
     * 历史订单查询
     * @param ordersHistoryDTO
     * @return
     */
    @ApiOperation("历史订单查询")
    @GetMapping("/historyOrders")
    public Result<PageResult> page(OrdersHistoryDTO ordersHistoryDTO){
        log.info("历史订单查询{}", ordersHistoryDTO);
        PageResult pageResult = orderService.pageQuery4User(ordersHistoryDTO);
        return Result.success(pageResult);
    }

    /**
     * 查询订单详细
     * @param id
     * @return
     */
    @ApiOperation("查询订单详细")
    @GetMapping("/orderDetail/{id}")
    public Result<OrderVO> getOrderDetail(@PathVariable Long id){
        log.info("查询订单信息：{}",id);
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 取消订单
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation("取消订单")
    @PutMapping("/cancel/{id}")
    public Result cancelOrder(@PathVariable Long id) throws Exception{
        log.info("取消订单id：{}",id);
        orderService.cancel(id);
        return Result.success();
    }

    /**
     * 再来一单
     * @param id
     * @return
     */
    @ApiOperation("再来一单")
    @PostMapping("/repetition/{id}")
    public Result repetition(@PathVariable Long id){
        log.info("再来一单id：{}",id);
        orderService.repetition(id);
        return Result.success();
    }

    /**
     * 催单
     * @param id
     * @return
     */
    @ApiOperation("催单")
    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable Long id){
        log.info("催单:{}",id);
        orderService.reminder(id);
        return Result.success();
    }
}
