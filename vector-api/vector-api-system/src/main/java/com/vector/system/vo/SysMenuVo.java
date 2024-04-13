package com.vector.system.vo;

import com.vector.system.entity.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuVo extends SysMenu {

    private List<SysMenuVo> children;
}
