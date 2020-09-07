### Action éfféctuées dans l'application


1- Création du repo pour le p4.
2- commit de l'application non corrigé et des infos techniques : sprint dashboard + kit tecnique onBoarding.
3- centralisation de la version de log4j dans les properties du pom.xml.
4- MAJ de la version de dépendance de log4j core et api.
5- Spécification zone UTC ajouté directement dans le jdbc de connection à la bdd de test et en prod dans le package config et TestConfig, fichier DataBaseConfig. la ligne est la suivante : ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC. L16
6- Ajout des methodes : calculateFareCarWithLessThirtyMinutes() & calculateFareBikeWithLessThirtyMinutes() afin de verifier si le parking est gratuit en deça de 30mn Test OK
7- Correction de la logique de temps dans la classe FareCalculatorService suppression des variable qui stockait l'heure d'entrée et de sortie et de la variable duration qui 
est egale a out - in parti supprimé : 
				int inHour = ticket.getInTime().getHours();
        			int outHour = ticket.getOutTime().getHours();
			        int duration = outHour - inHour;


Correctifs apportés :  // On obtient le temps passé dans le parking en Millis
		       double duration = ticket.getOutTime().getTime() - ticket.getInTime().getTime();
		       //On divise le resultat par 3 600 000 qui equivaut à une heure en Millis afin de le convertir sur une base 1.
		       double difference = duration / 3600000;
		       /Mise á zéro de la difference pour permettre au parking d'etre gratuit si inferieur à 30mn
      			 if(difference <= 0.5 ) {
           			difference = 0;
				}

8- Formatage du prix du parking a n² decimal apres la virgule dans la class ParkingService :     private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
//Appel de la method de formatage sur le resultat afin d'avoir 2 nb apres la virgule.
System.out.println("Please pay the parking fare:" + decimalFormat.format(ticket.getPrice()) + " $ ");

9- Ajout de la methode de test CalculateFareBikeMoreThanOneMonth() &  public void calculateFareCarMoreThanOneMonth() afin de verifier si le vehicule reste un jour une semaine ou un mois rien ne plante.	
	
10- Dans les constantes ajout de : and t.OUT_TIME is NULL afin qu'en cas de doublon on sort bien le doublon qui n'est pas encore sorti


