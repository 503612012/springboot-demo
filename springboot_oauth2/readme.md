**TODO LIST**
- [x] https://www.e-learn.cn/content/java/2082138
- [x] https://blog.csdn.net/qq_37139980/article/details/90605708
- [x] https://blog.csdn.net/banat020/article/details/85083869
- [x] https://www.jianshu.com/p/19059060036b
- [ ] https://www.jianshu.com/p/d9e6609d8510
- [ ] https://www.jianshu.com/p/18b6ec524d4d
- [ ] https://www.cnblogs.com/wunaozai/p/9205550.html
- [ ] https://my.oschina.net/merryyou/blog/1803206
- [ ] https://www.jb51.net/article/162325.htm
- [ ] https://www.cnblogs.com/LOVE0612/p/9913336.html
- [ ] https://www.xttblog.com/?p=3732
- [ ] https://segmentfault.com/q/1010000007787576/a-1020000007788788
- [ ] https://blog.51cto.com/13698036/2394773?source=dra
- [ ] https://www.liangzl.com/get-article-detail-28562.html
- [ ] https://yq.aliyun.com/articles/650250

#### 1. 获取token
```
http://localhost:8080/oauth/token?username=admin&password=123456&client_id=dev&client_secret=password&grant_type=password
```
#### 2. 刷新token
```
http://localhost:8080/oauth/token?refresh_token=869dd3de-caf4-4e0b-a0e2-4ce13e91c4bd&client_id=dev&client_secret=dev&grant_type=refresh_token
```

#### 3. 获取资源
```
http://localhost:8080/hi?name=Oven&access_token=57794a40-86ac-4be1-9076-0da769ebae11
```