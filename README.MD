
# BANK ACCOUNT KATA  
  
  
## Consignes
  
Le kata suivant est composé d'une partie obligatoire, et d'un certain nombre d'éléments facultatifs que vous pouvez ajouter si vous avez le temps de le faire et si vous le souhaitez.  

**Mode opératoire**
Le kata vous sera fourni lorsque vous le voudrez, à partir de ce moment la vous aurez environ 1/2 journée pour nous fournir votre implémentation (du lundi fin d'après midi au mardi matin par exemple).

Une fois le kata mis à disposition nous ferons une première revue de votre code, suite à cette revue nous organiserons un entretien de debiref pour discuter du code que vous avez produit. Pendant cet entretien vous pourriez être amener à écrire du code (demande d'évolution ou correction par exemple)

Le repository du kata se trouve ici : [Bank Account Kata](https://github.com/kirinux/bank-account)
vous pouvez soit cloner le repository soit faire une merge request comme expliqué plus bas.

**Livraison**
Le kata devra être fourni sous forme d'un repository [github](https://github.com) ou bien faire l'objet d'une *merge request* sur le repository d'origine
  
 **Quelques éléments de travail:**  
* Si il vous manque une information, faite un choix (hypothèse) et utiliser la  
* Attention à bien réaliser les uses cases à minima 
* Il n'y à pas de "bonne" façon de faire, nous sommes intéressé par votre raisonnement, votre technique et la façon dont vous écrivez du code

**Containtes techniques**
* Java 8 minimum
* le pom.xml contient quelques dépendances obligatoires
* Pas d'autres contraintes techniques, vous pouvez utiliser les outils/librairies/framework que vous souhaitez



## le Kata

### Minimum valuable product

**US1**

> En tant que client d’une banque, je veux pouvoir déposer des sous sur mon compte

Règles de gestion
* On ne peut pas déposer de somme < 0

**US2**

> En tant que client d’une banque, je veux pouvoir retirer des sous sur mon compte

Règles de gestion
* Par défaut, pas de découvert autorisé, donc pas de retrait qui ferait passer le solde en négatif

**US3**

> En tant que client d’une banque, je veux pouvoir consulter le solde de mon compte

Règles de gestion
* Aucunes


### Fonctionnalités supplémentaires
Les fonctionnalités suivantes sont des propositions de features optionnelles à ajouter à votre Kata, elles n'ont pas de priorité particulière, vous pouvez implémenter ce qui vous semble intéressant, ou même proposer d'autres améliorations

**API**
Exposer nos 3 use case via une API accessible en HTTP

**Sécuriser l'API**
Si vous avez choisis d'exposer les services via une API, proposer une méthode de sécurisation de cette API

**Non blocking API**
Si vous avez choisis d'exposer les services via une API, proposer une implémentation non bloquantes de ces services

**Tests métiers (BDD)**
Ajouter des tests BDD à votre application.

**Gradle ou Maven**
Si vous le souhaitez vous pouvez migrer vers Gradle et nous expliquer pourquoi
