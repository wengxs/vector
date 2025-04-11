package com.vector.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vector.wms.pojo.entity.WmsReceive;
import com.vector.wms.pojo.query.WmsReceiveQuery;
import com.vector.wms.pojo.vo.WmsReceiveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WmsReceiveMapper extends BaseMapper<WmsReceive> {

    WmsReceiveVO selectVOById(Long id);

    IPage<WmsReceiveVO> selectVOPage(@Param("page") IPage<?> page, @Param("q") WmsReceiveQuery query);
}
