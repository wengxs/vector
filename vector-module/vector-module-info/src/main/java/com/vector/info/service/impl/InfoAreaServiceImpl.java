package com.vector.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vector.info.entity.InfoArea;
import com.vector.info.mapper.InfoAreaMapper;
import com.vector.info.service.InfoAreaService;
import org.springframework.stereotype.Service;

@Service
public class InfoAreaServiceImpl extends ServiceImpl<InfoAreaMapper, InfoArea> implements InfoAreaService {

}
