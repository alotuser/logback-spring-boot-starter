# logback-spring-boot-starter
## 日志链路追踪分组

### 应用背景：

随着系统架构的日益复杂，特别是采用了分布式架构后，这些问题变得更加难以追踪和解决。微服务架构、分布式中间件（如消息队列、分布式缓存、对象存储）以及跨区域调用等，都增加了问题排查的难度。

1.分布式跟踪：传递TraceID实现全链路追踪

2.用户行为分析：记录用户ID、操作类型等信息

3.API请求跟踪：关联请求参数与响应结果

4.异步任务处理：跨线程传递上下文信息

5.安全审计：记录操作者身份信息
 


### Maven项目的pom.xml的dependencies中加入以下内容:
``` xml
<dependency>
    <groupId>com.github.alotuser</groupId>
    <artifactId>logback-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
### demo代码演示
``` java
//配置服务
@Service
public class Loginer implements BaseUser{
	@Override
	public String getUserId() {
		return "123";//根据项目自定义id
	}
}

//使用
BaseLogger log= BaseLoggerFactory.getLogger(getClass());
@RequestMapping(value = "/")
String hello() {
    	
  String msg="Hello World!";

  log.debug(msg);
  log.info(msg);
  log.error(msg);
  log.warn(msg);
  log.trace(msg);
  return "Hello World!";
}



```


