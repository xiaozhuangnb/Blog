# Blog
基于若依框架进行二次开发的一个博客项目
优化点：
 问题：
      beforeUpload 逻辑可能与 404 错误间接相关：
      图片文件没有被上传到服务器：beforeUpload 中只是将图片转换为 base64 并保存到 this.options.img，并未真正上传到服务器。如果后续逻辑没有将 this.options.img 上传到服务器，那么服务器上可能没有该图片文件，导致访问该图片时出现 404 错误。
      没有调用上传接口：如果图片没有在 beforeUpload 后被上传，或上传路径有误（比如缺少日期目录），服务器上就不会有这张图片。因此在浏览器尝试通过 GET 请求去加载时，返回 404 是正常的。
      在 beforeUpload 中读取图片并显示预览后，应调用上传接口，将 base64 格式的图片文件上传到服务器，确保服务器能够保存该图片文件。
      检查上传后的响应结果（通常包含文件名、文件路径等），并将服务器返回的文件路径作为 src 属性应用到图片元素上。
  解决：
      接收上传文件：
      使用 @RequestParam("avatarfile") MultipartFile file 接收前端传递的文件 avatarfile。
      检查文件是否为空：
      如果文件不为空，则进行后续处理。
      获取当前登录用户：
      使用 getLoginUser() 方法获取当前登录的用户信息。
      上传文件：
      调用 FileUploadUtils.upload 方法，将文件上传到 RuoYiConfig.getAvatarPath() 指定的路径。
      删除原有头像文件：
      拼接路径 RuoYiConfig.getProfile()，并删除旧头像文件（FileUtils.deleteFile(filePath)）。
      更新用户信息：
      调用 userService.updateUserAvatar 方法更新数据库中用户的头像信息，并更新缓存中的用户头像数据。
      返回上传结果：
      如果更新成功，返回 AjaxResult.success()，并包含 imgUrl 字段，用于前端显示新头像。
项目问题还待发掘 欢迎Star  更多也可以参考若依官方文档：https://doc.ruoyi.vip/ruoyi/
## 系统环境需求

- JDK 17
- MySQL  5.7
- Maven 3.6
- Node 17

## 技术选型

1、系统环境

- Java EE 8
- Servlet 3.0
- Apache Maven 3.6

2、主框架

- Spring Boot 2.2.x
- Spring Framework 5.2.x
- Spring Security 5.2.x

3、持久层

- Apache MyBatis 3.5
- Hibernate Validation 6.0
- Alibaba Druid 1.2

4、视图层

- Vue 2.6
- Axios 0.21
- Element 2.15

#运行
前端文件夹：blog-vue
运行命令
cd blog-vue
npm i
npm run serve
后端文件夹：blog-admin
修改blog-admin\src\main\resources\application-druid.yml文件
            master:
                url: jdbc:mysql://localhost:3306/（自己的数据库名字）?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
                username: （数据库名字）
                password: （数据库密码）

数据库文件：vue-blog-master\sql\vue-blog.sql
                
