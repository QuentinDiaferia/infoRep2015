.PHONY : all

all : util client serveur cleanrep

client : util
	cp -r Util/classes/ Client/
	javac -sourcepath Client/src/ -cp Client/classes/ -d Client/classes/ Client/src/*.java

serveur : util
	cp -r Util/classes/ Serveur/
	javac -sourcepath Serveur/src/ -cp Serveur/classes/ -d Serveur/classes/ Serveur/src/*.java

util :
	javac -sourcepath Util/src/ -cp Util/classes/ -d Util/classes/ Util/src/bureau/*.java
	javac -sourcepath Util/src/ -cp Util/classes/ -d Util/classes/ Util/src/Fenetre.java

clean :
	rm -f -r Serveur/classes/*.class Serveur/classes/bureau/
	rm -f -r Client/classes/*.class Client/classes/bureau/
	rm -f -r Util/classes/*.class Util/classes/bureau/

cleanrep :
	rm -r Util/classes/*
