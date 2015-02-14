#InfoRep2015

Bureau distribué
======================
Dates importantes :
----------------------
|Rendu|Date|
|:---|:--|
|Spécifications|18/02|
|Conception|25/03|
|Projet|12/05|

Résumé des spécifications :
------------------------------
L'application prend en charge les intéractions au clavier et à la souris.

Nous déveloperons un bureau distribué avec plusieurs applications : 
- un bloc-notes;
- une calculatrice très simple;
- une galerie de photos;
- un widget météo (fixe sur pluie);

Ces applications sont sous la forme de widgets. Elles ne sont pas redimensionnables mais on peut les fermer. 

On définit des maîtres de fenêtre. Lorsqu'un utilisateur clique sur ou dans une fenêtre, il en devient maître jusqu'à quelques secondes après qu'il ait perdu le focus.

Le nombre de clients maximum en simultané est limité arbitrairement à 3 car nous ne pourrons pas tester avec plus mais ça pourrait fonctionner (ça dépend du serveur et du réseau).
