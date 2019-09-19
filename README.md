##安静笔记

##部署
###依赖
- Git安装（拉代码）
- JDK (编译)
- Maven (构建项目)
- Mysql (数据库)

## 步骤
- yum update (更新系统插件)
- yum install git （安装git）
- mkdir App (root下创建代码文件夹)
- cd App
- git clone https://gitee.com/xinhong8156/community.git(拉代码)
- yum install maven（安装maven）
- yum v (查询maven版本)
- mvn comile package(编译打包，clean可以去掉)
- vim application.properties文件或者复制修改application-production.properties文件后重新打包
- mvn package
- java -jar -Dspring.porfiles.active-production target/community-0.0.1-SNAPSHOT.jar (运行配置为application-production.properties的jar包)


##资料
[maven]{https://mvnrepository.com/tags/maven}
[Spring文档]{https://spring.io}
[码云授权登录]{https://gitee.com/oauth/applications/new}
[富文本编辑java代码]（三个尖角号+java+三个尖角号）
//```+java+回车
//代码
//+```

##工具

[http请求处理]｛https://square.github.io/okhttp/｝
[富文本编辑器]｛https://pandao.github.io/editor.md/｝
[富文本编辑器使用文档github地址]｛https://github.com/pandao/editor.md｝
热部署

mybatis逆向工程导入
1.配置完想xml信息后执行在terminal中执行mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
2.生成的.xml文件中可能存在代码重复，需处理

ajax请求：JSON.stringify()将json对象转成字符串形式传递到服务器，@RequestBody将接收到的字符串反序列化成对象,进行对象处理