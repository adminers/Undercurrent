# 功能说明

1. 缓存（redis、ehcache、caffeine）演示

2. springfox-swagger2+swagger-bootstrap-ui 演示

3. 待升级为Spring Boot （2.2.x+）、Knife4j（2.0.6+）

4. log4j2演示

5. 解决Apache Log4j-2任意代码执行漏洞

   临时处置建议-禁用lookup属性。2.10.0以及以上版本在log4j2.component.properties配置文件中修改`log4j2.formatMsgNoLookups = true`，2.9.x版本，升级至2.10.0，再进行配置
   
   注意：禁用lookup功能，date，java，marker，ctx，main，jvmrunargs，sys，env，log4j等属性会被禁用。默认情况下使用`logger.info("Try ${date:YYYY-MM-dd}")`，会将`${date:YYYY-MM-dd}`打印成当前时间。禁用lookup功能后，会将消息字符串保存原样，在日志中输出`Try ${date:YYYY-MM-dd}`。
   

- 最简单的跨域 @CrossOrigin
