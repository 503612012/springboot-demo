### 1.启动容器

```shell
docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin
```

### 2.web端查看

> 浏览器打开 http://127.0.0.1:9411