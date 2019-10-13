##安静笔记

##部署
###依赖
- Git安装（拉代码）
- JDK (编译)
- Maven (构建项目)
- Mysql (数据库)
- 广告位
- 注册谷歌AdSense

## 步骤
- yum update (更新系统插件)
- yum install git （安装git）
- mkdir App (root下创建代码文件夹)
- cd App
- git clone https://gitee.com/xinhong8156/community.git(拉代码)
- yum install maven（安装maven）
- yum v (查询maven版本)
- mvn compile package(编译打包，clean可以去掉)
- vim application.properties文件或者复制修改application-production.properties文件后重新打包
- mvn package
- java -jar -Dspring.porfiles.active-production target/community-0.0.1-SNAPSHOT.jar (运行配置为application-production.properties的jar包)
-  本地打包报错后：mvn clean package -Dmaven.test.skip=true

java -Xdebug -Xrunjdwp:transport=dt_socket,address=8082,server=y,suspend=y -jar -Dspring.porfiles.active-production target/community-0.0.1-SNAPSHOT.jar

#git常用命令1
- 创建分支（develop），防止影响原运行代码：git checkout -b develop
- 查看代码状态：git status
- 查看分支：git branch
- 提交暂存区：git add .
- 提交代码：git commit -m "添加视频及详情页码"
- 推到远端：git push
- 如果说没有对应的远端。执行：git push --set-upstream origin develop

#git常用命令2
git 合并分支到master
假如我们现在在dev分支上，刚开发完项目，执行了下列命令

git add .
git commit -m ‘dev'
git push -u origin dev

然后我们要把dev分支的代码合并到master分支上 该如何？ 
首先切换到master分支上
git checkout master
如果是多人开发的话 需要把远程master上的代码pull下来
git pull origin master
如果是自己一个开发就没有必要了，为了保险期间还是pull
然后我们把dev分支的代码合并到master上
git merge dev
然后查看状态
git status
On branch master
Your branch is ahead of 'origin/master' by 12 commits.
(use "git push" to publish your local commits)
nothing to commit, working tree clean

##资料
[maven]{https://mvnrepository.com/tags/maven}
[Spring文档]{https://spring.io}
[码云授权登录]{https://gitee.com/oauth/applications/new}
[富文本编辑java代码]（三个尖角号+java+三个尖角号）
[热度话题]本节算法定时任务+topN计算 ------处理查询时页码携带参数
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