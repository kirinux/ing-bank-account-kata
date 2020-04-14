# Bank Account Kata (EN)
_Une version [française est disponible](#bank-account-kata-fr)_
> This is a home exercice, with some implementation freedom and a due date, its goal is to give a starting point for the upcoming technical interview.  

## Instructions
This Kata has a mandatory part, the [MVP or Minimum Valuable Product](#mvp---minimum-valuable-product)
and some [bonus features](#bonus-features), so you can use the time left to shine.  
In case of missing information, make a choice and stay consistent with it.    
_Be careful with the size and the message of your commits._

**There is no "good" way to make this Kata, we're interested in your implementation choices and your technical skills with those constraints.**

### Delivery
The Kata should be delivered using a [Github](https://github.com) repository, or you can create a *Pull Request* to the [develop](https://github.com/kirinux/ing-bank-account-kata/tree/develop) branch of the [original repository](https://github.com/kirinux/ing-bank-account-kata).  
Your repository should have: 
* A branch named as your `trigram` received by mail, it should contain your final source code.
* A `readme.md` file, explaining the possible intricacies of your implementation and how to start your project.

### Technical constraints
* Java@8 minimum
* JUnit@5 + AssertJ@3

## The Kata
### MVP - Minimum Valuable Product

#### User Story 1
> As a bank, deposit money from a customer to his account, is allowed when superior to €0.01

#### User Story 2
> As a bank, withdraw money from a customer account, is allowed when balance won't go negative

#### User Story 3
> As a bank, a customer can display its account balance

#### User Story 4
> As a bank, a customer can display its account transactions history

### Bonus features
The below features are optionals and non-exhaustives.  
There is no priority between them, you can implement the ones you want and even propose yours.

#### REST API
* Suggest a REST API using http to operate services realized in the MVP
* Secure your API
* Use a non-blocking solution

#### Customers & Accounts
* The bank has multiple customers
* A customer can have multiple accounts

#### Persistence
* Suggest a data persistence solution

#### User Interface
* Suggest a UI to operate services realized in the MVP

#### Build
* Use Gradle instead of Maven
* Suggest a build system for the project
* Suggest End to End tests for your artifact

----------

# Bank Account Kata (FR)
> Ceci est un exercice assez libre d'implémentation à faire chez vous, dans un temps restreint, il a pour objectif d'ouvrir la discussion technique lors d'un futur entretien.

## Consignes
Le Kata suivant est composé d'une partie obligatoire, le [Minimum Valuable Product ou MVP](#minimum-valuable-product---mvp)
et de [fonctionnalités facultatives](#features-bonus) afin d'utiliser le temps restant pour vous démarquer.  
S'il vous manque une information, faites un choix et restez cohérent avec celui-ci.  
_Faites attention à la taille de vos commits et leurs messages._

**Il n'y a pas de "bonne" façon de réaliser ce Kata, nous sommes intéressés par votre choix d'implémentation, votre technique et le respect des contraintes.**

### Livraison
Le Kata devra être fourni sous forme d'un repository [Github](https://github.com) ou bien faire l'objet d'une *Pull Request* vers la branche [develop](https://github.com/kirinux/ing-bank-account-kata/tree/develop) du [repository d'origine](https://github.com/kirinux/ing-bank-account-kata).  
Votre repository doit contenir : 
* Une branche qui a pour nom, votre `trigramme` reçu par mail, qui contiendra votre code source final.
* Un fichier `readme.md` qui explique les possibles subtilités de votre implémentation et comment lancer votre projet.

### Contraintes techniques
* Java@8 minimum
* JUnit@5 + AssertJ@3

## Le Kata
### Minimum Valuable Product - MVP

#### User Story 1
> En tant que banque, j'accepte le dépôt d'argent d'un client vers son compte s'il est supérieur à 0,01€

#### User Story 2
> En tant que banque, j'accepte le retrait d'argent d'un client depuis son compte si le solde ne devient pas négatif

#### User Story 3
> En tant que banque, j'offre la possibilité à mon client de consulter le solde de son compte

#### User Story 4
> En tant que banque, j'offre la possibilité à mon client de consulter l'historique des transactions sur son compte

### Features bonus
Les fonctionnalités suivantes sont optionnelles et non exhaustives.  
Elles n'ont pas de priorité entre elles, vous pouvez implémenter celles qui vous intéressent ou même en proposer d'autres.

#### API REST
* Proposer une API REST consommable via http pour interagir avec les services réalisé dans le MVP
* Sécuriser l'API
* Utiliser une solution non-bloquante

#### Clients & Comptes
* La banque a plusieurs clients
* Un client peut avoir plusieurs comptes

#### Persistence
* Proposer une solution de persistence des données

#### Interface Utilisateur
* Proposer une interface graphique pour interagir avec les services réalisés dans le MVP

#### Build
* Utiliser Gradle au lieu de Maven
* Proposer un system de build pour le projet
* Proposer des tests End to End à destination de votre livrable
