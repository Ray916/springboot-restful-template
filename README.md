## Spring Boot Template

`com.xuezw.template.sys`包下面为系统安全认证机制，采用JWT（Json Web Token）；

具体可参考博客：[重拾后端之Spring Boot（四）：使用JWT和Spring Security保护REST API](http://www.jianshu.com/p/6307c89fe3fa)

`com.xuezw.template.hero`包为事例Demo；

### 角色

采用用户与角色多对多的的关系（Hibernate的ManyToMany），角色有三种：ADMIN, USER, ACTUATOR；

ADMIN: 为管理员角色；

USER：为普通用户角色；

ACTUATOR：为监控角色，使用该角色可以查看spring boot actuator对应用的监控信息；

