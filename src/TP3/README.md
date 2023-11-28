## Question 13
> 1.  Le mapping d'URL, et même d'URI, en requête GET avec les méthodes des controlleurs se fait via l'annotation @GetMapping("url")
>     devant la méthode contenant le comportement associé à l'URL. Une même URl peut appeler diverses méthodes selon les méthodes HTTP
>     utilisé (GEt, PUT, DELETE...). RequestMapping permet de faire match une méthode à une URL, quelque soit la méthode HTTP.
>     Voir plus de détails [ici](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html) et [ici](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html)
> 2.  La partie de la méthode permettant d'identifier la vue a afficher se trouve dans le return.
>     En effet, la méthode développé ici retourne une String, qui indique le chemin relatif vers la vue, à partir du
>     dossier `/src/main/resources/templates/`. Ainsi, "return greeting" recherchera, à l'aide du ViewResolver, 
>     le fichier greeting.html. Le ViewResolver va chercher par défaut dans le dossier templates énoncé plus haut.<br />
>     cf [ce lien](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html)
>     pour plus de détails sur les retours possibles de méthodes de controlleurs.
> 3.  En indiquant dans les paramètres de la méthode l'annotation @RequestParam(), cela permet d'indiquer à Spring
>     d'injecter dans la variable taggé de l'annotation une valeur récupéré via les paramètres servlet (paramètres de requetes,
>     géré par TomCat), plus de détails  [ici](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html).<br />
## Question 17
> Le fait de tagguer @Entity indique à JPA que la classe est mappée à une table du même nom que cette dernière.
> Ainsi, lors du lancement du serveur, la BDD H2 en mémoire va être update par JPA pour intégrer une table
> correspondant à la classe annotée.<br />
> Les classes taggués sont des POJO (Plain Old Java Object), assez similaire au Beans.
> La principale différence est que le constructeur par défaut d'un POJO peut etre protected,
> alors que celui d'un Beans doit être public. Le POJO n'a pas forcément ses attributs accessibles via des gets et sets, mais
> ces derniers sont nécessaires pour être mappés par JPA. Ainsi, un attribut sans ses accesseurs ne sera pas stockés en BDD.<br />
> Hibernate est là pour faire cette génération en BDD, c'est un ORM (mapping objet relationnel), qui permet d'automatiser
> la persistance vers une BDD.<br />
> cf [ce lien pour plus de détails](https://spring.io/guides/gs/accessing-data-jpa/).<br />
> Voici les exigeances pour une classe pour être [un JavaBean](https://en.wikipedia.org/wiki/JavaBeans#JavaBean_conventions),
> et pour être une [Entity](https://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html#bnbqb).
## Question 20
> Il faut en premier lieu rajouter "spring.jpa.defer-datasource-initialization=true" dans
> le fichier application.properties. Les données sont ensuite donc bien insérés dans la table.
> La ligne rajouté permet d'attendre qu'un bean de EntityManagerFactory soit initialisé, ce qui permet de faire patienter
> la lecture et insertion des données. Cela permet à hibernate de générer un EntityManager, et donc de d'abord générer les tables sur la BDD en mémoire.
> cf [ce lien pour une explication sur l'instruction](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.data.spring.jpa.defer-datasource-initialization)
## Question 22
> "Autowired" permet d'indiquer une injection de dépendance, permettant à Spring de gérer
> l'injection, presque de la même manière que si l'on déclarait le constructeur avec en paramètre l'attribut
> autowired. La différence est que l'injection et l'affectation des champs autowired se fait après construction du bean
> (cf [la documentation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html),
> section Autowired Fields)
## Question 30
> Il existe 3 méthodes pour obtenir bootstrap, en utilisant par exemple un lien côté HTML pour télécharger à l'ouverture de la page
> bootstrap. La seconde méthode est d'ajouter directement le fichier minimifié dans l'arborescence à la main.
> Le mieux cependant reste de rajouter les dépendances dans le pom.xml, pour récupérer les webjars. <br />
> C'est cette dernière méthode qui est préféré ici, car permet une résolution automatique de la dépendance.
> Il faut cependant se rendre compte que l'on utilise pas les CDN, et que c'est notre site qui héberge le webjar, pouvant rendre
> plus lent le download de la page. En effet, les CDN sont optimisés pour être téléchargé rapidement par l'utilisateur
> (divers serveurs partout sur le globe), et le fichier peut même rester en cache.<br />
> Cependant, la troisème méthode a été préféré aussi car c'était une nouvelle méthode pour inclure le bootstrap<br />
> [cf ce lien pour une explication plus précise sur les trois méthodes.](https://www.codejava.net/frameworks/spring-boot/add-bootstrap-and-jquery-in-a-spring-boot-project)