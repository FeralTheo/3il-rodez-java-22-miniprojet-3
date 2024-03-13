---
titre: Java
sous-titre: Miniprojet 3 - Pendu avec Swing
auteur: Philippe \textsc{Roussille}
date: 3iL 1A 2023
---

**Date de rendu du projet : 13/03/2024 - 17h05**

J'ai rencontré plusieurs problèmes :

. J'ai eu une mauvaise conception entre JeuxDuPendu et LogiquePendu. Cela m'a empéché de faire en sorte que le programme puisse redémarer après une victoire ou une défaite
    
. Actuellement le jeux redémarre après une victoire ou défaite mais il ne tire pas un nombre aléatoire correct.

. J'ai mal métrisé GitHub sur ce projet, contrairement à d'habitude je n'ai pas fait de push régulié et j'ai est péyé le prix fort.

. Il manque le choronomètre et la gestion de la difficulté.


Mais :

.J'ai eu le temp de réaliser des tests unitaire de base.

.Le programme fonctionne avec une bonne partie des éléments attendues


Conclusion : 

.En raison d'un problème avec git est d'une mauvaise gestion personnelle j'ai mal utilisé GitHub, je pense que c'est ma plus grosse erreur.

## Fonctionnalités attendues

1. Lecture aléatoire d'un mot à deviner à partir d'un fichier texte donné à la racine du projet. Fait
2. Affichage graphique de l'interface du jeu à l'aide de Swing.                                  Fait
3. Affichage graphique du pendu qui évolue en fonction des erreurs du joueur.                    Fait
4. Affichage graphique des lettres déjà proposées par le joueur.                                 Fait
5. Affichage (ou non) de la définition (niveau de difficulté).                                   Non
6. Utilisation (ou non) d'un timer (niveau de difficulté).                                       Non
7. Gestion des entrées utilisateur pour proposer des lettres.                                    Fait
8. Vérification de la validité des entrées utilisateur (lettre de l'alphabet uniquement).        Non
9. Gestion du décompte des tentatives restantes.                                                 Fait
10. Gestion de la victoire ou de la défaite du joueur.                                           Fait
11. Possibilité de rejouer une partie après la fin d'une partie.                                 En partie
