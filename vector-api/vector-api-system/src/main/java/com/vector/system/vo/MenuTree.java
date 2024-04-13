package com.vector.system.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by Zorg
 * 2020/6/7 04:48
 */
@Data
public class MenuTree {

    private Long id;

    private String name;

    private List<MenuTree> children;

}
