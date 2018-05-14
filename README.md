## TP - Nouvelles technologies de la répartition

Samuel Bazniar
Thomas Delhaye
Robin Dupret

Pour la réalisation de cette application, nous avons tout d'abord généré un
projet Spring Boot. Nous avons ensuite réalisé le contrôleur principal
(i.e. `UsersController`) en le dotant de 4 actions :

* `index` : permettant de lister tous les utilisateurs. Exemple :

  ~~~
  $ curl -X GET http://localhost:8080/users/
  ~~~

* `show(int id)` : permettant de récupérer un utilisateur en particulier.
  Exemple :

  ~~~
  $ curl -X GET http://localhost:8080/users/1
  ~~~

* `credit(int id, double montant)` : permettant de créditer le compte d'un
  utilisateur en spécifiant son ID et un montant. Exemple :

  ~~~
  $ curl -X POST http://localhost:8080/users/1/credit --data "montant=12.15"
  ~~~

* `debit(int id, double montant)` permettant de débiter le compte d'un
  utilisateur en spécifiant son ID et un montant. Par défaut le montant est
  accepté en valeur positive mais peut aussi être spécifié en valeur
  négative. Exemple :

  ~~~
  $ curl -X POST http://localhost:8080/users/1/debit --data "montant=30"
  ~~~

Nous avons implémenté les différentes routes en créant les méthodes
listés plus haut dans le contrôleur et en utilisant les annotations
suivantes :

* `@RequestMapping` : pour définir le motif de la route (en indiquant
  notamment entre crochets les variables présentes dans la route)
* `@PathVariable` : pour définir et avoir la possibilité de récupérer
  une variable présente dans la route côté code (l'ID de l'utilisateur
  dans notre cas)
* `@RequestParam` : pour définir le fait que l'on attend de la requête
  la présence d'un paramètre en particulier (le montant dans notre cas).

Afin de gérer les utilisateurs, nous n'utilisons pas de base de données
mais un `ArrayList` qui est géré par le biais d'une classe `UsersService`.
Ce service est marqué en tant que tel avec l'annotation `@Service` et est
ensuite instancié du côté de l'application (i.e. dans le contrôleur) par
le biais de l'annotation `@Autowired`.

Pour finir, nous avons réalisé des test unitaires avec le framework JUnit.
La génération d'un projet Spring Boot nous a permis de voir comment les tests
étaient écrits et avec quelques recherches, nous avons pu écrire le code
nécessaire pour tester le fonctionnement de notre application.

Par exemple :

~~~java
public void creditUser() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post("/users/1/credit")
        .param("montant", "10")
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(equalTo("Ok")));

}
~~~

Ce test permet de tester que l'accès à la méthode `credit` se déroule
correctement pour l'utilisateur possèdant l'ID n°1.
