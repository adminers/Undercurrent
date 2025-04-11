package com.qiaweidata.un.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2095123635162090269L;

    private Map<String, Map<String, String>> dimColumnMap;

    private Map<String, List<String>> columnDataMap;

    private List<List<String>> insertDatas;

    private List<List<String>> showDatas;

    private StringBuilder whereSql = new StringBuilder(" where 1 = 1 ");

    private List<String> whereParams = new ArrayList<>();

    private List<String> columns = new ArrayList<>();

}