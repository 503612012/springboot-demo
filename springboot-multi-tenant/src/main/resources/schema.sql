create table t_datasource_config
(
    id                int          not null auto_increment primary key,
    driver_class_name varchar(255) null,
    url               varchar(255) null,
    _name             varchar(255) null,
    username          varchar(255) null,
    _password         varchar(255) null,
    initialize        tinyint(1)   null
);

insert into t_datasource_config values (1, 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/db_oauth?characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true', 'zhangsan', 'root', 'root', 0);
insert into t_datasource_config values (2, 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/db_test?characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true', 'lisi', 'root', 'root', 0);
insert into t_datasource_config values (3, 'com.mysql.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/db_jxc?characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true', 'wangwu', 'root', 'root', 0);