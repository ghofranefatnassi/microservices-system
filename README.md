# Portail Intelligent de Gestion des Ressources Hydriques Régionales

## Description du projet
Ce projet est une application web basée sur une **architecture microservices** pour la gestion des ressources hydriques régionales. L'application permet :

- La gestion des réservoirs (volume, niveau actuel, localisation)
- La distribution de l’eau aux régions en fonction des quotas et besoins
- La visualisation de la disponibilité et suivi des ressources
- La communication **synchrone** entre microservices pour récupérer les données et **asynchrone** via événements pour notifier les variations de niveaux

Le projet respecte les bonnes pratiques de développement : séparation back-end/front-end, configuration centralisée, scalabilité et tolérance aux pannes.

---

## Architecture

### Microservices
| Microservice       | Port  | Entités principales                          | Description |
|-------------------|------|---------------------------------------------|-------------|
| Eureka Server      | 8080 | N/A                                         | Service de découverte des microservices |
| Gateway            | 9095 | N/A                                         | Point d’entrée unique pour accéder aux microservices |
| Réservoirs         | 8081 | Réservoir, MesureNiveau                      | Gestion des réservoirs et suivi des niveaux |
| Distribution       | 8083 | Région, PlanDistribution                     | Gestion de la distribution d’eau aux régions |

### Communication
- **Synchrone** : Distribution interroge Réservoirs pour obtenir le volume disponible
- **Asynchrone** : Réservoirs publie des événements de variation de niveau

---

## Technologies utilisées
- **Back-end** : Spring Boot, Spring Cloud (Eureka, Gateway), Spring Data JPA, RabbitMQ (pour events)
- **Base de données** : MySQL
- **Front-end** : Angular
- **Conteneurisation** : Docker
- **Orchestration** (optionnel) : Kubernetes

---

## Prérequis
- Java 17 ou supérieur
- Maven
- Node.js + Angular CLI
- Docker & Docker Compose
- MySQL

---

## Installation & Exécution

### Base de données
Créer une base MySQL pour chaque microservice :

```sql
CREATE DATABASE reservoir_db;
CREATE DATABASE distribution_db;
