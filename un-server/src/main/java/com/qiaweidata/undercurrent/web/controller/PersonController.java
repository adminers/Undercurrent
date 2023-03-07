package com.qiaweidata.undercurrent.web.controller;

import com.qiaweidata.undercurrent.web.dao.PersonMapper;
import com.qiaweidata.undercurrent.web.entity.Machine;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement  // 需要事务的时候加上
@RestController
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping("/save")
    public String save() {
        Machine machine = new Machine();
        machine.setId(UUID.randomUUID().toString().replace("-", ""));
        machine.setSystemName("补补补补");
        machine.setParentId("");
        machine.setIconPath("");
        machine.setCreateTime(new Date());
        machine.setCreateUser("");
        machine.setUpdateTime(new Date());
        machine.setUpdateUser("");
        personMapper.insert(machine);
        return machine.getId();
    }


}
