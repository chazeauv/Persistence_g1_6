```markdown
## Projet fil rouge PERSISTENCE_G1_6

L'objectif de ce projet est d'implémenter et d'appliquer les différentes notions vues en cours de Patron et Composant. Celui-ci a pour base une frame où l'on peut y disposer des cercles, des carrés et des rectangles. Au fil de chaque itération, il est demandé d'ajouter de nouvelles fonctionnalités, mettre en place de différents patrons (patron Composite, Visiteur, ...), d'utiliser Java/Maven/Sonar mais aussi d'avoir une couverture de test d'au moins 30%. Par ailleurs, afin d'éviter une redondance d'information, un dossier spécifique contraindra nos différents diagrammes de séquence au fur et à mesure des incrémentation.

## Table des matières
- Titre et Description
- Table des Matières
- Installation
- Utilisation
- Conception et réalisation
- Documentation
- Test
- Itération 1
- Itération 2
- Itération 3
- Itération 4

## Installation
- Assurez-vous d'avoir Maven installé sur votre système.
- Importez le projet vers votre IDE préféré.
- Dans un terminal, lancez la commande `mvn clean install` afin de construire le projet et d'installer les dépendances.
- Et enfin, lancez le projet (fichier App.java).

## Utilisation
Comme mentionné dans la description plus haut, ce programme permet de dessiner des cercles, des carrés et des ronds. Il est par ailleurs possible de les déplacer, les supprimer (Ctrl-Z) ou d'en faire des groupes.

## Conception et réalisation
- Base du projet fournie par l'IM2AG de Grenoble (création de la frame, ajout des figures et de l'interface graphique).
- CHAZEAU Vincent (alias Vin's) conception/réalisation et implémentation des incrémentations imposées en cours.
- CHARRE Kyllian (alias Kyk's) conception/réalisation et implémentation des incrémentations imposées en cours.

## Documentation
Durant tout le projet, il est fait en sorte d'utiliser le moins de commentaires possible. La raison en est que le code parle de lui-même, cela passe par une nomenclature explicite, un suivi des conventions ainsi que par une organisation logique du code. Les noms de variables, de fonctions et de classes sont choisis de manière à refléter clairement leur fonction et leur rôle au sein du projet. Cependant, en cas de nécessité, des commentaires sont ajoutés de manière à fournir des informations importantes ne pouvant pas être déduites directement du code.

## Test
Pour visualiser la couverture de test, il faut au préalable être connecté au VPN de l'UGA (ou physiquement sur un ordinateur de l'UGA) :
- Installer `Cisco AnyConnect Secure Mobility Client`.
- Se connecter à `vpn.grenet.fr` en tant qu'étudiant ou personnel (identifiant et mot de passe seront nécessaires).

Puis il suffit d'accéder à la page d'accueil de notre projet (`http://im2ag-sonar.u-ga.fr:9000/dashboard?id=fr.uga.miage.m1%3APersistence_g1_6`) pour pouvoir visualiser le pourcentage de couverture.

## Itération 1

### Objectif

- Découverte du projet / Prise en main des fonctionnalités de base.
- Implémenter la notion de "patron Visiteur" vue en cours.
- Permettre d'exporter la figure créée au format XML ou JSON.
- Connecter son projet à Sonar afin de suivre l'avancée de la dette technique, "smell code", et la couverture de test.
- Baisser autant que possible la dette technique.

### Problèmes rencontrés

Les problèmes rencontrés ont été minimes, mais présents lors de la mise en pratique du patron Visiteur. Le principal problème a été de bien s'imprégner du projet et de l'architecture générale du code.

### Résultat

Tous les objectifs ont été remplis et la dette a été réduite, passant de 4h à moins d'une heure.

## Itération 2

### Objectif

- Mettre en place le fonctionnement du Ctrl-Z.
- Adapter le code pour qu'il suive la logique du patron Composant.
- Diagramme de classes.
- Diagramme de séquence.
- Ajout de tests de couverture du code.
- Non-régression des précédentes fonctionnalités.

### Problèmes rencontrés

//TODO Vincent, help, je ne sais pas quoi dire.

### Résultat

Tous les objectifs ont été réalisés, tout en maintenant une dette technique d'environ 1h et sans régression des précédentes fonctionnalités.

## Itération 3

### Objectif

- Rendre les formes déplaçables sur la frame (clic + maintien sur une forme, déplacement, relâcher).
- Non-régression des précédentes fonctionnalités.

### Problèmes rencontrés

- Mauvaise mise en place du patron Composant à l'itération précédente, soit remodellement pour être à jour.
- La composition des groupes est très complexe et implique une longue phase de conception afin d'en faire ressortir toutes les facettes (Ctrl-Z sur un groupe annule le groupe, déplacement sur une forme du groupe = déplacement de toutes les formes).
- L'objectif de non-régression des précédentes fonctionnalités devient de plus en plus complexe (exponentielle par rapport au nombre d'incrémentation du projet). À VOIR SI ÇA A UN SENS CETTE PHRASE, JE N'EN SUIS PAS CONVAINCU.

### Résultat

TODO MA BABY

## Itération 4

### Objectif

- Ajout d'une librairie externe ajoutant une nouvelle forme dessinable (un cube).
- Le plus rapidement possible.
- Non-régression des précédentes fonctionnalités.

### Problèmes rencontrés

- Appliquer le même comportement au cube qu'aux autres formes.
- Non-régression.

### Résultat

La forme a été ajoutée avec succès et dans le temps imparti, elle est conforme avec le reste du projet (suit la même logique et

 fonctionnement que toutes les autres formes).
```
