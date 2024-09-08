package com.caizhidao.mapper;

import com.github.pagehelper.Page;
import com.caizhidao.dto.GoodsSalesDTO;
import com.caizhidao.dto.OrdersPageQueryDTO;
import com.caizhidao.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 分页条件查询并按下单时间排序
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单基础数据
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    /**
     * 根据状态查询订单数
     * @param status
     * @return
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer getCountByStatus(Integer status);

    /**
     * 根据订单状态和下单时间查询
     * @param status
     * @param orderTime
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 根据动态条件统计营业额数据
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 根据动态条件统计订单数
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 根据动态条件统计菜品销量
     * @param beginTime
     * @param endTime
     * @return
     */
    List<GoodsSalesDTO> getSaleTop10(LocalDateTime beginTime, LocalDateTime endTime);
}
