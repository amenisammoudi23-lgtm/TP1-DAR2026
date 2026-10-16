# TP1 - Architecture Réseau (Serveur / Client)

##  Interprétation des résultats de l'application Serveur

Lorsque l'on exécute la classe `Server`, la console affiche le message suivant :
> "Je suis un serveur en attente de la connexion d'un client..."

### Analyse technique :
1. **Création du `ServerSocket`** : Le serveur réserve un port d'écoute sur la machine (dans notre cas, le port `1234`).
2. **Phase de blocage (`accept`)** : L'exécution s'arrête au niveau de l'instruction `serverSocket.accept()`. Le serveur entre dans un état d'attente active (*blocking state*), guettant l'arrivée d'une requête de connexion émanant d'une application cliente.
3. **Persistance** : Tant qu'aucun client ne se connecte, la console n'affiche rien de plus. Dès qu'un client se connectera, le message *"Un client est connecté"* s'affichera avant de libérer les ressources et de fermer proprement les sockets.

##  Application Client

### 1. Code Source de la classe `Client`
Le client initialise une connexion TCP vers le serveur en précisant l'hôte (`localhost` ou `127.0.0.1`) et le port d'écoute (`1234`).

### 2. Interprétation des résultats dans la console
* **Côté Serveur** :
    1. Affiche *"Je suis un serveur en attente de la connexion d'un client..."*
    2. Dès que le client s'exécute, le serveur affiche instantanément *"Un client est connecté"*.
* **Côté Client** :
    1. Affiche *"Je suis un client pas encore connecté…"*
    2. Passe ensuite à *"Je suis un client connecté"* dès que la liaison avec le socket du serveur est établie avec succès.

### 3. Utilisation d'une adresse IP (`127.0.0.1`)
En remplaçant `"localhost"` par `"127.0.0.1"`, on cible directement l'adresse IP de loopback IPv4 de la machine locale. La connexion s'établit de la même manière.

## Exécution du Serveur
Lorsque l'on lance la classe `Server`, le programme se met en écoute sur le port `1234` :
![Console du Serveur](./images/a.png)



##  Exécution du Client
Une fois le client lancé, il se connecte au serveur. Voici le résultat dans les consoles :
![Console du Client](./images/b.png)