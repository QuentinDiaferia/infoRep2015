.PHONY : all

all : util client serveur cleanrep

client : util
	cp -r classes/ Client/
	javac -sourcepath Client/src/ -cp Client/classes/ -d Client/classes/ Client/src/*.java

serveur : util
	cp -r classes/ Serveur/
	javac -sourcepath Serveur/src/ -cp Serveur/classes/ -d Serveur/classes/ Serveur/src/*.java

util :
	mkdir -p classes
	javac -sourcepath src/ -cp classes/ -d classes/ Util/src/Widget.java
	javac -sourcepath src/ -cp classes/ -d classes/ Util/src/WidgetBlocNote.java
	javac -sourcepath src/ -cp classes/ -d classes/ Util/src/Bureau.java

clean :
	rm -f -r Serveur/classes/*.class Serveur/classes/bureau/
	rm -f -r Client/classes/*.class Client/classes/bureau/
	rm -r classes

cleanrep :
	rm -r classes
