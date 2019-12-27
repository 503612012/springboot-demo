create table t_datasource_config
(
    id              int          not null
        primary key,
    driverclassname varchar(255) null,
    url             varchar(255) null,
    _name           varchar(255) null,
    username        varchar(255) null,
    _password       varchar(255) null,
    initialize      tinyint(1)   null
);

INSERT INTO db_test.t_datasource_config (id, driverclassname, url, _name, username, _password, initialize) VALUES (1, 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/db_oauth?characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true', 'zhangsan', 'root', 'root', 0);
INSERT INTO db_test.t_datasource_config (id, driverclassname, url, _name, username, _password, initialize) VALUES (2, 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/db_test?characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true', 'lisi', 'root', 'root', 0);
INSERT INTO db_test.t_datasource_config (id, driverclassname, url, _name, username, _password, initialize) VALUES (3, 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/db_jxc?characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true', 'wangwu', 'root', 'root', 0);