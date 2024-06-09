# Groupe-Chat
Ce projet consiste en la réalisation d’une application de messagerie de type "Group Chat". Chaque utilisateur peut voir les messages envoyés par tous les autres utilisateurs connectés.


#Fonctionnalités

Messagerie de groupe : Permet à chaque utilisateur de voir les messages envoyés par tous les participants.

Informations sur les messages : Les messages incluent le nom de l’utilisateur et la date d’envoi (par exemple : Alice dit "Bonjour!" le 12/12/2023 à 14h25).

Architecture décentralisée : Chaque machine connectée au chat joue un rôle symétrique et communique directement avec les autres, sans passer par un serveur central.

Communication TCP indépendante : Chaque message est traité comme une communication TCP distincte. Pour chaque message envoyé entre deux utilisateurs (par exemple, Alice et Bob), un socket de java.io est créé, le message est envoyé via ce socket, puis le socket est fermé.

Paradigme orienté objet : Le projet est réalisé en utilisant des principes de programmation orientée objet, en évitant de concentrer tout le code dans la fonction main et en créant des 
classes appropriées.

# Programmes
Sender : Permet d’envoyer des messages via l’entrée console standard.
Receiver : Réceptionne les messages envoyés et les affiche à l’écran.
