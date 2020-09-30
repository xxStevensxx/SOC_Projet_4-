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
11- Suppression de la modif n*10 et ajout de :  public static final String GET_TICKET = "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE from ticket t,parking p where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=? order by t.in_time desc limit 1"; afin d'obtenir le ticket par ordre d'entrée dans l'ordre decroissant.

12- Ajout de l'encodage  directement dans la class Util.Scanner pour eviter les bugs d'encodage (FindBugs).
13-Création du package com.parkit.parkingsystem.shell.integration et de sa classe test 
14-çréation des classes de test: InteractiveShellIT, ParkingDataBaseIT
15-creation des methodes de tests dans la classe ParkingDataBaseIt
16- Création du package com.parkit.util afin de tester la class inputreaderutil et de sa classe test
17- correction de 7 bogues findBugs :

	1*Found reliance on default encoding in com.parkit.parkingsystem.util.InputReaderUtil.<static initializer for InputReaderUtil>(): new java.util.Scanner(InputStream)
	2*Ecriture d'un champ statique com.parkit.parkingsystem.util.InputReaderUtil.scan depuis la méthode d'une instance com.parkit.parkingsystem.util.InputReaderUtil.setScan(Scanner
	3*Unread public/protected field: com.parkit.parkingsystem.service.FareCalculatorService.inputReaderUtil
	4*La méthode com.parkit.parkingsystem.model.Ticket.getInTime() risque d'exposer sa représentation interne en renvoyant com.parkit.parkingsystem.model.Ticket.inTime
	5*La méthode com.parkit.parkingsystem.model.Ticket.getOutTime() risque d'exposer sa représentation interne en renvoyant com.parkit.parkingsystem.model.Ticket.outTime
	6*La méthode com.parkit.parkingsystem.model.Ticket.setInTime(Date) risque d'exposer sa représentation interne en stockant un objet externe modifiable dans com.parkit.parkingsystem.model.Ticket.inTime
	7*La méthode com.parkit.parkingsystem.model.Ticket.setOutTime(Date) risque d'exposer sa représentation interne en stockant un objet externe modifiable dans com.parkit.parkingsystem.model.Ticket.outTime

