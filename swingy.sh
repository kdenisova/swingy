docker pull mysql

docker run -d --name swingy --restart on-failure -e MYSQL_ROOT_PASSWORD=nimfa -e MYSQL_DATABASE=swingy -p 3306:3306 mysql

cat swingy.sql | docker exec -i swingy /usr/bin/mysql -u root --password=nimfa swingy