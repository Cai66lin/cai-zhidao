package com.caizhidao.mapper;


import com.caizhidao.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 插入套餐菜品关系
     * @param setmealDishes
     */
    void insert(List<SetmealDish> setmealDishes);

    /**
     * 批量删除套餐关联菜品数据
     * @param setmealIds
     */
    void deleteWithSetmealId(List<Long> setmealIds);

    /**
     * 根据套餐ID查询与菜品对应关系数据
     * @param setmealId
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
