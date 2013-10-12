wechart_weigou
==============

微微狗微信公共平台

##普通的不能再普通的SSH项目。网站和微信公共平台结合在了一起

开发工具：MyeclipseForSpring 10
开发环境：JDK1.7 + MySQL6.0 + Tomcat 7

##框架简介
1、基本的SSH项目。使用xml文件的方式对action进行配置。使用注解的方式配置model和service。
   本框架是在MyEclipseForSpring10，jdk1.7 javaEE5 的环境下开发，运行环境为Mysql6.0，tomcat7 ，请使用相应配置和软件以避免不必要的错误和麻烦。
2、封装了BaseService，这是一个公有类，直接继承即可。
   封装的方法有get、load、find、pageModel、getBySql。
   关于分页的实现是使用getPageModel方法,详细使用方法参考注释。
3、封装了BaseAction。
   内有BaseService、id、List<Object>、message、PageModel等关于分页的对象。
   建议有关网站的Action都继承此类。
4、包简介
    freepander.action 存放action类
    freepander.filter 存放过滤器
    freepander.model 存放model和WxMsg类
    freepander.model.recv  存放微信接收消息的模型
    freepander.model.send  存放微信发送消息的模型
    freepander.service  存放service类
    freepander.service.parse 存放解析微信消息类
    freepander.util  存放一些常用的工具类
    freepander.wechart  存放关于微信消息处理的逻辑
    applicationContext.xml spring的配置文件
    jdbc.properties  数据库的配置问价，存放了数据库名、用户名、密码、端口等信息
    log4j.properties 日志的配置文件
    struts.xml action 的配置文件

##微信消息处理
微信消息发送到WeigouAction中，判断是post类型还是get类型。

###GET类型为接口验证
 1、从请求中获取signature、timestamp、nonce、echostr等信息。
 2、调用WeiXin.access()方法进行验证
 3、正确返回echostr，错误输出日志。
 4、验证完成
其中微信的Token存放在freepander.util.Constants类中。
###POST方法为介绍到消息
 1、获得请求输入流，调用freepander.util.WeiXin.recv()方法将输入流转换为WeRecvMsg类对象
 2、调用freepander.wechart.Portal.main()方法对消息进行处理，并返回恢复的消息WxSendMsg.
  2.1、在freepander.wechart.Portal.main()中判断消息类型
  2.2、对消息对象进行转换，传入相应的处理类中，并返回要回复的消息。
 3、调用freepander.util.WeiXin.send()方法，将要回复的消息WxSendMsg对象发送到相应的输出流中。
 4、与微信的一次通信完成。

##其他信息
1、本项目可在SAE平台上运行，只需更改数据库配置信息即可。
但日志方面有问题。
2、微信恢复方面使用了"小贱贱数据接口"http://api.xiaojianjian.net/

项目持续完善中，目标是做成个微商城。


