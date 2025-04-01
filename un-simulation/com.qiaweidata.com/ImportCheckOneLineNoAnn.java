/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.afterturn.easypoi.test.entity.check;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ImportCheckOneLineNoAnn
{
    @Excel(name = "姓名")
    private String name;
    
    @Excel(name = "性别")
    private String sex;
    
    @Excel(name = "年纪")
    private String age;
    
    @Excel(name = "爱好")
    private String hobby;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSex()
    {
        return sex;
    }
    
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    public String getAge()
    {
        return age;
    }
    
    public void setAge(String age)
    {
        this.age = age;
    }
    
    public String getHobby()
    {
        return hobby;
    }
    
    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }
}
