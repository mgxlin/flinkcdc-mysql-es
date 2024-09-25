启动项目前的准备步骤：

1、修改连接配置：在项目中更新 MySQL 和 Elasticsearch 的连接配置，默认设置为 localhost。

2、初始化数据库：在 sql 文件夹中运行初始化脚本，以设置必要的数据库结构和数据。

3、启动应用程序：运行 FlinkCDCApplication 启动应用程序。

4、验证数据一致性：访问 localhost/1，检查 Elasticsearch 中的数据是否与 MySQL 数据库中的一致。

5、数据同步测试：新增或修改 MySQL 表中的数据，然后检查 Elasticsearch 中的数据，验证同步是否成功。
