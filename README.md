# swingy

Implementation of a minimalistic textual RPG game in Java. 
The game design follows the [Model-View-Controller](https://en.wikipedia.org/wiki/Model–view–controller) architecture and allow switching between the console view and GUI view. 
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

     `cat swingy.sql | docker exec -i swingy /usr/bin/mysql -u root --password=nimfa swingy`

3. Build

     `mvn clean package`

3. Launch
     
     `java -jar target/swingy.jar [console/gui]`

## Rules

You can create a new hero or continue game with saved heroes.  After choosing a hero, the actual game begins. The initial position of the hero is in the center of the map. Each turn he can move one position in one of the 4 four directions: 
* North
* East 
* South
* West

To move the hero, use Up, Down, Left and Right keys in GUI View or N, E, S and W in Console View.

When a map is generated, villains of varying power will be spread randomly over the map. When a hero moves to a position occupied by a villain, the hero has 2 options:
* Fight, which engages him in a battle with the villain
* Run, which gives him a 50% chance of returning to the previous position. If the odds aren’t on his side, he must fight the villain.

If a hero looses a battle, he loose hit points depends on villain’s power, or he dies and also looses the mission. 

If a hero wins a battle, he gains:
* Experience points, based on the villain power. Of course, he will level up if he reaches the next level experience.
* An artifact. Of course, winning a battle doesn’t guarantee that an artifact will be dropped and the quality of the artifact also varies depending on the villain’s strength.

Can you reach Level 6 and win the Game?

## Screenshots


![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/Menu.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/NewGame.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/Playground.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/FightOrRun.jpeg)

![alt text](https://github.com/kdenisova/swingy/blob/master/screenshots/Console.jpeg)
