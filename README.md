# swingy

Implementation of a minimalistic textual RPG game in Java. 
The game design follows the Model-View-Controller architecture and allow switching between the console view and GUI view. 
Hero's stats store in relational database.

You can play game in two different modes:

     -console: text (or terminal) mode

     -gui: graphical mode

## Database

* [MySQL](https://hub.docker.com/_/mysql) docker container.
* [Hibernate](https://hibernate.org)


## How to use?

1. Create MySQL docker container

     `docker pull mysql`
     
     `docker run -d --name swingy -e MYSQL_ROOT_PASSWORD=nimfa -e MYSQL_DATABASE=swingy -p 3306:3306 mysql`

2. Add database

     `cat backup.sql | docker exec -i swingy /usr/bin/mysql -u root --password=nimfa swingy`

3. Build

     `mvn clean package`

3. Launch

     `cd target`
     
     `java -jar swingy.jar [console/gui]`

## Screenshots


![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/Menu.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/NewGame.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/Playground.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/FightOrRun.jpeg)
