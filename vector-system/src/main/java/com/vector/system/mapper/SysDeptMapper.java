package com.vector.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.system.pojo.entity.SysDept;
import com.vector.system.pojo.query.SysDeptQuery;
import com.vector.system.pojo.vo.SysDeptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 部门 Mapper
 * @author wengxs
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    SysDeptVO selectVOById(Long id);

    IPage<SysDeptVO> selectVOPage(@Param("page") IPage<?> page, @Param("q") SysDeptQuery query);
}
