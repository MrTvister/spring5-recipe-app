--# docker run --name mysqlDB -v /home/tvist/DockerData/MySQL:/var/lib/mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3306:3306 -d mysql

CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;
CREATE User 'sfg_dev_user'@'localhost' IDENTIFIED BY 'tvist';
CREATE User 'sfg_prod_user'@'localhost' IDENTIFIED BY 'tvist';
CREATE User 'sfg_dev_user'@'%' IDENTIFIED BY 'tvist';
CREATE User 'sfg_prod_user'@'%' IDENTIFIED BY 'tvist';
GRANT SELECT, INSERT, DELETE, UPDATE on sfg_dev.* to 'sfg_dev_user'@'%';
GRANT SELECT, INSERT, DELETE, UPDATE on sfg_prod.* to 'sfg_prod_user'@'%';
GRANT SELECT, INSERT, DELETE, UPDATE on sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE on sfg_prod.* to 'sfg_prod_user'@'localhost';