package com.vector.info.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.vector.info.InfoApplication;
import com.vector.info.entity.InfoArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InfoApplication.class)
class InfoAreaServiceTest {

    @Autowired
    private InfoAreaService infoAreaService;

    @Test
//    @Transactional
    public void test() throws IOException {
        ClassPathResource resource = new ClassPathResource("pca-code.json");
        JSONReader reader = JSONReader.of(resource.getInputStream(), StandardCharsets.UTF_8);
        JSONArray provinceArr = reader.readJSONArray();
        int count = 0;
        for (int i = 0; i < provinceArr.size(); i++) {
            JSONObject provinceObj = provinceArr.getJSONObject(i);
            InfoArea province = new InfoArea();
            province.setCode(provinceObj.getString("code"));
            province.setName(provinceObj.getString("name"));
            province.setLevel(1);
            province.setFullName(province.getName());
            province.setParentCode("");
            System.out.println(provinceObj.getString("code") + ":" + provinceObj.getString("name"));
            count++;
//            infoAreaService.save(province);
            JSONArray citiesArr = provinceObj.getJSONArray("children");
            for (int i1 = 0; i1 < citiesArr.size(); i1++) {
                JSONObject cityObj = citiesArr.getJSONObject(i1);
                InfoArea city = new InfoArea();
                city.setCode(cityObj.getString("code"));
                city.setName(cityObj.getString("name"));
                city.setLevel(2);
                city.setFullName(province.getFullName() + city.getName());
                city.setParentCode(province.getCode());
                System.out.println(cityObj.getString("code") + ":" + cityObj.getString("name"));
                count++;
//                infoAreaService.save(city);
                JSONArray areasArr = cityObj.getJSONArray("children");
                for (int i2 = 0; i2 < areasArr.size(); i2++) {
                    JSONObject areaObj = areasArr.getJSONObject(i2);
                    InfoArea area = new InfoArea();
                    area.setCode(areaObj.getString("code"));
                    area.setName(areaObj.getString("name"));
                    area.setLevel(3);
                    area.setFullName(city.getFullName() + area.getName());
                    area.setParentCode(city.getCode());
                    System.out.println(areaObj.getString("code") + ":" + areaObj.getString("name"));
                    count++;
//                    infoAreaService.save(area);
                }
            }
        }
        System.out.println(count);
    }
}