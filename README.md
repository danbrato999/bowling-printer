## Ten-Pin Bowling Printer

This is a library developed to be able to read the info of a match of bowling from a text file, and print it to console.
The file will contain N number of lines, each line containing the name of the player and the amount
of pins knocked down in that shot. [Example file](https://github.com/danbrato999/bowling-printer/blob/master/src/test/resources/test-game.txt)

After processing the file, the library should print the scores according to these guidelines:
* For each player, print their name on a separate line before printing that player's pinfalls and score.
* All values are tab-separated.
* The output should calculate if a player scores a strike ('X'), a spare ('/') and allow
  for extra chances in the tenth frame.

This library makes heavy use of Java 8 streams, and provides several interfaces in order to easily
extend the core features.

There are several unit and integration tests, covering the principal possible scenarios.

A convenient jar is provided under _/dist_. Just use the following command to easily run the program:
> java -jar bowling-printer.jar [path_to_file] 

A new jar can be generated to the /target folder by running **mvn package**
