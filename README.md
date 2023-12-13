## Table des matières
- [Description du projet](#projet-fil-rouge-persistence_g1_6)
- [Installation](#installation)
- [Utilisation](#utilisation)
  - [Utilisation specifique des groupes](#Utilisation-specifique-des-groupes)
    - [Principe](#Principe)
    - [Regle](#Regle)
    - [Fonctionnement](#Fonctionnement)
- [Conception et réalisation](#Conception-et-realisation)
- [Documentation](#documentation)
- [Test](#test)
- [Documents de conception](./DIAGRAMME/)
- [Itération 1](#iteration-1)
- [Itération 2](#iteration-2)
- [Itération 3](#iteration-3)
- [Itération 4](#iteration-4)
- [Itération 5](#iteration-5)

  
## Projet fil rouge PERSISTENCE_G1_6

L'objectif de ce projet est d'implémenter et d'appliquer les différentes notions vues en cours de Patron et Composant. Celui-ci a pour base une frame où l'on peut y disposer des cercles, des carrés et des rectangles. Au fil de chaque itération, il est demandé d'ajouter de nouvelles fonctionnalités, mettre en place différents patrons (patron Composite, Visiteur, ...), d'utiliser Java/Maven/Sonar mais aussi d'avoir une couverture de test d'au moins 30%. Par ailleurs, afin d'éviter une redondance d'information entre moodle et le README, un dossier spécifique dans ce git contraindra nos différents diagrammes au fur et à mesure des incrémentations.


## Installation
- Assurez-vous d'avoir Maven installé sur votre système, la procédure est décrite [ici](https://maven.apache.org/install.html).
- Importez le projet vers votre IDE préféré.
- Dans un terminal, lancez la commande `mvn clean install` afin de construire le projet et d'installer les dépendances.
- Et enfin, lancez le projet (fichier App.java).


## Utilisation
Comme mentionné dans la description plus haut, ce programme permet de dessiner des cercles, des carrés, des ronds et des cubes. Il est par ailleurs possible de déplacer les formes, de faire des retours en arrière (Ctrl-Z) ainsi que de faire des groupes (assemblage de plusieurs formes, déplaçable comme une seule et même forme). Il est possible d'exporter l'état du plan de travail au format JSON ou XML ainsi que d'importer un plan de travail au même format.

#### Utilisation specifique des groupes
##### Principe
Il est possible de former des groupes de une forme ou plusieurs formes, soit par exemple de définir qu'un triangle et un cercle ne sont qu'une seule est même forme.

##### Regles 
La seule contrainte imposée est qu'un groupe doit être déplaçable de la même manière qu'une forme simple, on a par conséquent choisi nous même le fonctionnement et règle de gestion relative au groupe.
  1. Une forme peut appartenir au maximum à un groupe, ou minimum à 0.
  2. Le bouton "group" :
      - Selectionné : permet de créer les groupes et de déplacer les formes ou les groupes.
      - Non-selectionné : permet de créer des formes et de les déplacer individuellement. 
     
##### Fonctionnement
Pour créer un groupe :
  1. Creer des formes.
  2. Cliquer sur le bouton "group".
  3. Presser le clic gauche + maintenir + déplacer la souris pour sélectionner pour sélectionner les formes à mettre dans votre groupe (de la même manière que la saisie de plusieurs fichiers sur votre bureau) puis relacher.
  4. Un bouton "Group N" va apparaître sur le menu gauche.

Pour déplacer un groupe :
  1. Sélectionner le bouton "group". (A partir de cette étape les groupes son d'ors et déjà déplaçables)
  1(bis). Le même principe que le drag and drop pour une forme simple est ensuite appliqué.
  2. Le menu gauche sert uniquement à titre informatif, afin d'identifier facilement quel forme appartient à quel groupe. Les formes du groupe selectionné auront les bords surlignés.
  


## Test
Pour visualiser la couverture de test, il faut au préalable être connecté au VPN de l'UGA (ou physiquement sur un ordinateur de l'UGA) :
- Installer `Cisco AnyConnect Secure Mobility Client`.
- Se connecter à `vpn.grenet.fr` en tant qu'étudiant ou personnel (identifiant et mot de passe seront nécessaires).

A la suite de cela, il sera possible d'accéder à la page d'accueil de notre projet [ici](http://im2ag-sonar.u-ga.fr:9000/dashboard?id=fr.uga.miage.m1%3APersistence_g1_6) pour pouvoir visualiser le pourcentage de couverture.


## Documentation
Durant tout le projet, nous essayons d'utiliser le moins possible de commentaires. La raison en est que le code parle de lui-même, cela passe par une nomenclature explicite, un suivi des conventions ainsi que par une organisation logique du code. Les noms de variables, de fonctions et de classes sont choisis de manière à refléter clairement leur fonctions et leur rôles au sein du projet. Cependant, en cas de nécessité, des commentaires sont et seront ajoutés de manière à fournir des informations importantes ne pouvant pas être déduites directement du code.


## Conception et realisation
- Base du projet fournie par l'IM2AG de Grenoble (création de la frame, ajout des figures et de l'interface graphique).
- CHAZEAU Vincent : conception/réalisation et implémentation des incrémentations imposées en cours.
- CHARRE Kyllian : conception/réalisation et implémentation des incrémentations imposées en cours.



## Iteration 1

### Objectifs

- Découverte du projet / Prise en main des fonctionnalités de base.
- Implémenter la notion de "patron Visiteur" vue en cours.
- Permettre d'exporter la figure créée au format XML ou JSON.
- Connecter son projet à Sonar afin de suivre l'avancée de la dette technique, "smell code", et la couverture de test.
- Baisser autant que possible la dette technique.

### Problèmes rencontrés

Les problèmes rencontrés ont été minimes, mais présents lors de la mise en pratique du patron Visiteur. Le principal problème a été de bien s'imprégner du projet et de l'architecture générale du code.

### Résultat

Tous les objectifs ont été remplis et la dette a été réduite, passant de 4h à moins d'une heure.




## Iteration 2

### Objectifs

- Mettre en place le fonctionnement du Ctrl-Z.
- Adapter le code pour qu'il suive la logique du patron Composant.
- Diagramme de classes.
- Diagramme de séquence.
- Ajout de tests de couverture du code.
- Non-régression des précédentes fonctionnalités.
- Réduire la dette.

### Problèmes rencontrés

Les problèmes majeurs ont été rencontré lors de la réalisation des différents diagrammes demandés. Notamment pour comprendre qu'est-ce qui est important de représenter à travers ces diagrammes.

### Résultat

Tous les objectifs ont été réalisés, tout en maintenant une dette technique d'environ 1h et sans régression des précédentes fonctionnalités.




## Iteration 3

### Objectifs

- Rendre les formes déplaçables sur la frame (clic + maintien sur une forme, déplacement, relâcher).
- Non-régression des précédentes fonctionnalités.
- Réaliser un système de groupement de formes (un groupe agira exactement de la même manière qu'une forme seule).
- Réduire la dette technique et les codes smell.

### Problèmes rencontrés

- Mauvaise mise en place du patron Composant à l'itération précédente, soit remodèlement pour être à jour.
- La composition des groupes est très complexe et implique une longue phase de conception afin d'en faire ressortir toutes les facettes (Ctrl-Z sur un groupe annule le groupe, déplacement sur une forme du groupe revient à déplacer toutes les formes de ce groupe, grouper un groupe devient un nouveau groupe).
- L'objectif de non-régression des précédentes fonctionnalités devient de plus en plus complexe (exponentielle par rapport à l'ajout de nouvelles fonctionnalités).


### Résultat

Malheureusement, tous les objectifs n'ont pas pu être respectés dans les délais impartis, en particulier la réalisation des groupes. Le problème lié à la gestion des groupes n'a pas été suffisamment réfléchi, ce qui a entraîné une mauvaise gestion du temps. Cette itération montre l'importance d'une bonne planification avant de mettre en œuvre une fonctionnalité complexe. Une gestion plus efficace du temps aurait pu être réalisée en identifiant et en traitant rapidement les problèmes potentiels liés à la composition des groupes.



## Iteration 4

### Objectifs

- Ajout d'une librairie externe ajoutant une nouvelle forme dessinable (un cube).
- Le plus rapidement possible.
- Non-régression des précédentes fonctionnalités.
- Maintenir un niveau de courverture de test supérieur à 30%.

### Problèmes rencontrés

- Appliquer le même comportement au cube qu'aux autres formes.
- Non-régression de plus en plus complexe.

### Résultat

La forme a été ajoutée avec succès et dans le temps imparti, elle est conforme avec le reste du projet (suit la même logique et fonctionnement que toutes les autres formes).

## Iteration 5

### Objectifs

- Ajout de la fonctionnalité d'importation au format XML.
- Composition d'un JAR pour la fonciotnnalité d'eportation au format XML.
- Non-régression des précédentes fonctionnalités.
- Maintenir un niveau de courverture de test supérieur à 30%.

### Problèmes rencontrés

- Appliquer le même comportement au cube qu'aux autres formes.
- Non-régression de plus en plus complexe.

### Résultat

La forme a été ajoutée avec succès et dans le temps imparti, elle est conforme avec le reste du projet (suit la même logique et fonctionnement que toutes les autres formes).
