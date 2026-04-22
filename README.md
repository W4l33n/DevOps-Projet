# DevOps-Projet
Projet hébergé sur Github.com

[![CI/CD Pipeline](https://github.com/W4l33n/DevOps-Projet/actions/workflows/ci.yml/badge.svg)](https://github.com/W4l33n/DevOps-Projet/actions)

## 1. Mise en place du projet
Chaque membre du groupe avait un compte GitHub, Diana s'est chargée de créer le projet sur son compte (administratrice) car c'est la seule personne à pouvoir associer notre projet à un service externe. Elle nous a ensuite ajoutés au projet en tant que collaborateurs.

## 2. Mise en place de GitHub action
Pour la configuration de la CI/CD via GitHub Actions, nous nous sommes aidés de cette ressource : https://tech-insider.org/fr/tutoriel-github-actions-ci-cd-2026/#toc-0. Le pipeline exécute automatiquement les tests et gère nos déploiements.

## 3. Outils utilisés
- **Git** pour versionner notre code source.
- **Maven** pour construire les différentes phases de notre projet.
- **JUnit 5** pour les tests unitaires.
- **Jacoco** comme outil d'évaluation de la couverture de code intégré à Maven (qui échoue si la couverture est insuffisante).
- Un dépôt **Github** pour héberger notre code source versionné.
- **SonarQube** : Utilisation des ressources données pour mettre en place l'analyse statique afin de détecter les bugs et code smells.
- **GitHub Packages** : Utilisé pour la livraison continue de la librairie construite par Maven.
- **Docker**: image docker avec une demonstration des différentes fonctionnalitées que propose notre bibliothèque

## 4. Liste des fonctionnalités fournies par notre service
Dans le cadre de ce projet, nous avons implémenté une bibliothèque de manipulation de tableaux multidimensionnels inspirée de NumPy (Python), développée en Java.
- **Création et Allocation :** Instanciation de tableaux de dimension 1D ou 2D via les méthodes `zeros(...)`, `ones(...)` et `arange(from, to, step)`.
- **Manipulation des caractéristiques :** Accès aux dimensions via `getNdim()`, `getShape()` et `getSize()`.
- **Accès aux données :** Méthodes `get(i)` et `get(i, j)` sécurisées contre les erreurs de dimension.
- **Addition Matricielle (`add`) :** Fonction d'addition stricte entre deux `Ndarray` de formes identiques.
- **Changement de Forme (`reshape`) :** Capacité redimensionner un tableau (passage de 1D à 2D ou inversement) en utilisant un système d'aplatissement transitoire.
- **Affichage :** Une redéfinition de `toString()` alignant visuellement les matrices pour un affichage plus clair.

## 5. Workflow
Pour le projet nous avons décidé de faire des branches pour chaque fonctionnalité par exemple une branche pour l'intégration continue, une branche pour créer la bibliothèque java, et ainsi de suite. 
Pour la validation il faut au moins qu'une personne fasse une revue du code que quelqu'un souhaite merger sur une branche autre que le main. Pour la branche main, toute l'équipe doit être au courant des changements à apporter et doit procéder à une revue du code stricte avec l'utilisation des **Pull Requests**.

## 6. Liste et description des images Docker produites
- **Nom Image**:mon-ndarray-demo
- **Description**: Demonstration de notre bibliothèque avec toutes les fonctionnalités.
- **Dépot(lien)**:https://github.com/W4l33n/DevOps-Projet/pkgs/container/devops-projet%2Fmon-ndarray-demo
- **Commande**:
#Télécharger l'image  
docker pull ghcr.io/w4l33n/devops-projet/mon-ndarray-demo:latest
  
#Exécuter l'image  
docker run ghcr.io/w4l33n/devops-projet/mon-ndarray-demo:latest


## 7. Feedback (Retour sur les différents outils utilisés)

- Le déploiement du package Maven sur GitHub Packages nous a confrontés aux règles de sécurité en ligne (erreurs 422 qui nous ont poussés à comprendre l'importance des identifiants `groupId` conformes).
