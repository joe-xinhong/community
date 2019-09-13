##苗苗社区


##资料
[maven]{https://mvnrepository.com/tags/maven}
[Spring文档]{https://spring.io}
[码云授权登录]{https://gitee.com/oauth/applications/new}

##工具

[http请求处理]https://square.github.io/okhttp/
热部署

mybatis逆向工程导入
1.配置完想xml信息后执行在terminal中执行mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
2.生成的.xml文件中可能存在代码重复，需处理

ajax请求：JSON.stringify()将json对象转成字符串形式传递到服务器，@RequestBody将接收到的字符串反序列化成对象,进行对象处理