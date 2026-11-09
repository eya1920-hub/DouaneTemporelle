# Douane Temporelle

Mini-projet (DS 1) de Programmation Mobile, 3ème BI, FSEGT.
Application Android qui simule un poste de douane pour les voyageurs du temps.

## Groupe

Projet réalisé en solo par **Eya Riahi** (groupe d'un seul membre).

## Les 3 activités

1. **MainActivity** : formulaire d'enregistrement avec le nom du voyageur,
   l'année de naissance, l'année de destination et le motif du voyage
   (Tourisme, Modification du passé, Fuite) choisi dans un Spinner.
   Le bouton "Inspecter le profil" lance l'Activité 2 avec un Intent explicite.
2. **VerificationActivity** : affiche le "passeport" du voyageur (nom, âge
   temporel calculé, motif) avec deux boutons : "Approuver le saut temporel"
   et "Refuser (Risque de paradoxe)". Chaque bouton lance l'Activité 3 avec
   le verdict (booléen) et le nom du voyageur.
3. **ResultActivity** : affiche le verdict. Le fond est vert si le saut est
   approuvé, rouge s'il est refusé, avec un message personnalisé :
   "Bon voyage [Nom] !" ou "[Nom] est en état d'arrestation temporelle".
   Le bouton "Nouveau voyageur" revient à la MainActivity en fermant les
   activités précédentes.

## Contraintes de l'énoncé respectées

- **Layout de l'Activité 2** : `LinearLayout` horizontal racine, avec une
  image (poids 0.3) à gauche et un `RelativeLayout` (poids 0.7) à droite.
- **Clés des `putExtra`** : préfixées par les 4 derniers chiffres d'une carte
  d'étudiant et construites par la méthode statique
  `MainActivity.creerCle(matricule, nomCle)`, sans clé écrite en dur.
- **Nombre premier** : le bouton "Approuver" est désactivé dès le chargement
  de l'Activité 2 si le motif est "Modification du passé" et que la différence
  entre l'année de destination et l'année de naissance est un nombre premier.
- **IDs** : tous les éléments d'interface (TextView, EditText, Button)
  contiennent les initiales `_er`.
- **Commentaires** : le code est commenté en arabe écrit avec des chiffres
  (arabizi).

## Fonctionnalités bonus

- Validation du formulaire : champs vides, nom sans chiffres, années à
  4 chiffres, année de destination supérieure à l'année de naissance.
- Avertissement (Toast) lorsque le motif est "Fuite".
- Icône et image différentes selon le verdict (check et avion si approuvé,
  croix et bouclier si refusé).

## Lancer le projet

1. Cloner le dépôt et l'ouvrir avec Android Studio.
2. Attendre la synchronisation Gradle.
3. Lancer l'application sur un émulateur ou un téléphone (bouton Run).