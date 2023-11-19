
## Projet fil rouge : Persistence_g1_6

L'objectif de ce projet est d'implémenter et d'appliquer les différentes notions vue en cours de Patron et Composant.
Celui-ci à pour base une frame où l'on peut y disposer des cerles, des carrés, et des rectangles.
Au fil de chaque itérations, il est demandé d'ajouter de nouvelle fonctionnalités, mittre en place de différents patrons (patron Composite, Visiteur, ...),
d'utiliser Java/Maven/Sonar mais aussi d'avoir une couverture de test de minimum 30%.
Par ailleurs afin d'éviter une redondance d'information, un dossier spécifique contrindra nos différent diagramme de séquence au fur et à mesure des incrémentations

## Table des matières
  - [Titre et Description](#Projet-fil-rouge-:-Persistence_g1_6)
  - [Table des Matières](#table-des-matières)
  - [Installation](#installation)
  - [Utilisation](#utilisation)
  - [Conception et réalisation](#Conception-et-réalisation)
  - [Documentation](#documentation)
  - [Test](#test)
  - [Iteration 1](#iteration-1)
  - [Iteation 2](#iteration-2)
  - [Iteration 3](#iteration-3)
  - [Iteration 4](#iteration-4)

## Installation
- Assurez vous d'avoir Maven d'installé sur votre systeme
- Importer le projet vers votre IDE préféré
- Dans un terminal, lancer la commande `mvn clean install` afin de construire le projet et d'installer les dépendances
- Et enfin, lancez le projet (fichier App.java)


## Utilisation
Comme fait mention dans la description plus haut, ce programme permet de dessiner des cercles, des carrés et des ronds.
Il est par ailleurs possible de les déplacer, les supprimer (ctrl-z) ou en faire des groupes.

## Conception et réalisation 
- Base du projet fournie par l'IM2AG de Grenoble (création de la frame, ajout des figures et de l'interface graphique)
- CHAZEAU Vincent (alias Vin's) conception/réalisation et implémentation des incréments imposés en cours.
- CHARRE Kyllian (alias Kyk's) conception/réalisation et implémentation des incréments imposés en cours.

## Documentation
Durant tout le projet, il est fait un sorte d'utiliser le moins de commentaire possible, la raison en est que le code parle de lui même, 
cela passe par une nomenclatures explicites, un suivis des conventions ainsi que par une organisation logique du code. Les noms de variables,
de fonctions et de classes sont choisis de manière à refléter clairement leur fonction et leur rôle au sein du projet.
Cependant, en cas de nécessité, des commentaires sont ajoutés de manière à fournir des informations importantes ne peuvant pas être déduites directement du code.

## Test
Pour visualiser la couverture de test, il faut au préalable être connecté au VPN de l'UGA (ou physiquement sur un ordinateur de l'UGA) :
- Installer `Cisco AnyConnect Secure Mobility Client`
- Se connecter à `vpn.grenet.fr` entant qu'étudiant ou personnel (identifiant et mot de passe seront nécessaire)

Puis il suffit d'accéder à la page d'acceuil de notre projet (`http://im2ag-sonar.u-ga.fr:9000/dashboard?id=fr.uga.miage.m1%3APersistence_g1_6`)
pour pouvoir visualiser le pourcecntage de couverture 

## Itération 1

#### Objectif 

- Découverte du projet / Prise en main des fonctionnalités de base
- Implémenter la notion de "patron Visiteur" vue en cours
- Permettre d'exporter la figure créer au format XML ou JSON
- Connecter son projet à Sonar afin de suivre l'avancé de la dette technique, "smell code", et couverture de tes
- Baisser autant que possible la dette technique

#### Problèmes rencontrés

Les problèmes rencontrés on été minime, mais présent lors de la mise en pratique du patron Visiteur.
Le principale problème a été de bien s'imprégner du projet et de l'architecture général du code.

#### Resultat 

Tous les objectifs ont été rempli et la dette a été réduite passant de 4h à moins d'une heure.

## Itération 2

#### Objectif 

- Mettre un place le fonctionnement du ctrl-z
- Adapter le code pour qu'il suive la logique du patron Composant
- Diagramme de classes
- Diagramme de séquence
- Ajout de test de courverture du code
- Non régression des précédentes fonctionnalités

#### Problèmes rencontrés

//TODO Vincent help je sais pas quoi dire
#### Resultat

Tous les objectifs ont été réalisés, tout en maintenant une dette technique autour de 1h et sans régression des précédentes fonctionnalités.

## Itération 3

#### Objectif 

- Rendre les formes déplacable sur la frame (clic + maintien sur une forme, déplacement, relacher)
- Non régression des précédentes fonctionnalités

  
#### Problèmes rencontrés

- Mauvaise mise en place du patron Composant à l'iération précédente, soit remodelement pour être à jour
- La composition des groupes est très complexe et implique une longue phase de conception afin d'en faire ressortir toutes les facettes (ctrl-z sur un groupe annule le groupe, deplacement sur une forme du groupe = deplacement de toutes les formes)
- L'objectif de non régression des précédentes fonctionnalités devient de plus en plus complexe (exponentielle par rapport au nombre d'incrémentation du projet) A VOIR SI CA A UN SENS CETTE PHRASE, J'EN SUIS APS CONVAINCU

#### Resultat

TODO MA BABY

## Itération 4

#### Objectif 

- Ajout d'une librairie externe ajoutant une nouvelle forme dessinable (un cube)
- Le plus rapidement possible
- Non régression des précédentes fonctionnalités
  
#### Problèmes rencontrés

- Appliquer le même comportement au cube qu'au autre forme
- Non régression

#### Resultat

La forme a été ajouté avec succé dans le temps imparti
