docker pull mysql

docker run -d --name swingy -e MYSQL_ROOT_PASSWORD=nimfa -e MYSQL_DATABASE=swingy -p 3306:3306 mysql