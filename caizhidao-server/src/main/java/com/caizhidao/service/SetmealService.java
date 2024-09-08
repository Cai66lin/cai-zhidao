package com.caizhidao.service;

import com.caizhidao.dto.SetmealDTO;
import com.caizhidao.dto.SetmealPageQueryDTO;
import com.caizhidao.entity.Setmeal;
import com.caizhidao.result.PageResult;
import com.caizhidao.vo.DishItemVO;
import com.caizhidao.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增菜品,同时插入套餐与菜品的关系
     * @param setmealDTO
     */
    void addSetmealWithDish(SetmealDTO setmealDTO);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 批量删除套餐
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    SetmealVO getSetmealById(Long id);

    /**
     * 启用禁用套餐
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);

}
