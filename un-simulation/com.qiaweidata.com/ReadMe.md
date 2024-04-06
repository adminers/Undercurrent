## java工程`文件或Jar方式`命令行启动

### class文件运行
进入 `\target\classes` 执行，**单元测试需拷贝test-classes文件到classes**

`注意系统间隔符的差异 windows; linux:` 

**windows**

```shell 
java -cp .;../lib/* com.fly.simple.MainRun
java -cp .;../lib/* com.fly.simple.MainRun2
java -cp .;../lib/* org.junit.runner.JUnitCore com.fly.simple.SimpleTest
```

**linux**

```shell
java -cp .:../lib/* com.fly.simple.MainRun
java -cp .:../lib/* com.fly.simple.MainRun2
java -cp .:../lib/* org.junit.runner.JUnitCore com.fly.simple.SimpleTest
```


### jar运行
#### `mvn clean package` 打包

- 直接运行

```shell
java -jar java-with-depend.jar
```

- 指定mainClass运行

```shell
java -cp java-with-depend.jar com.fly.simple.MainRun2
```

- 指定depend`(不推荐)`运行

```shell
java -cp lib/* -jar java-with-depend.jar
```

- 指定depend`(不推荐)`、mainClass运行

`需考虑系统间隔符的差异 windows; linux:`

```shell
java -cp lib/*;java-with-depend.jar com.fly.simple.MainRun
java -cp lib/*;java-with-depend.jar com.fly.simple.MainRun2
```

`无需考虑系统间隔符的差异`

```
java -cp lib/* -cp java-with-depend.jar com.fly.simple.MainRun
java -cp lib/* -cp java-with-depend.jar com.fly.simple.MainRun2
```

#### `mvn clean package -f pom-same-dir.xml` jar与depend同一目录打包

- 指定depend、mainClass运行

```shell
java -cp ./* com.fly.simple.MainRun
java -cp ./* com.fly.simple.MainRun2
```
