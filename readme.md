# 🤖 AI Dev Agent MCP

Un agent intelligent de développement qui automatise les tâches en utilisant l’IA (LLM), une architecture basée sur MCP (Model Context Protocol), et des technologies modernes comme Spring Boot, React et PostgreSQL.

---

## 🚀 Présentation

**AI Dev Agent MCP** est un assistant intelligent capable de :

* Comprendre des commandes en langage naturel
* Générer du code (Spring Boot, React…)
* Manipuler des fichiers et projets
* Exécuter des commandes système
* S'améliorer avec une mémoire et des suggestions intelligentes

---

## 🧠 Fonctionnement global

L’utilisateur envoie une commande → l’agent IA l’analyse → choisit un outil → exécute une action → retourne le résultat.

---

## 🎯 Fonctionnalités

### 🤖 Agent IA

* Compréhension du langage naturel
* Transformation en actions
* Intégration avec LLM (Mistral API)

---

### 🔧 MCP Tools

* 📁 File Tool → créer, supprimer, déplacer fichiers
* 🧱 Code Tool → générer code Spring / React
* 💻 Command Tool → exécuter commandes système
* 🔍 Analyzer Tool (future)

---

### 💾 Mémoire

* Stockage des interactions utilisateur
* Historique des commandes
* Amélioration du contexte

---

### ⚡ Mode Proactif

* Suggestions automatiques
* Détection des améliorations possibles
* Assistance intelligente

---

### 🔄 Feedback Loop

* Vérification des actions
* Gestion des erreurs
* Logs des opérations

---

### 💬 Frontend

* Interface chat (React)
* Historique des actions
* Affichage des résultats

---

## 🏗️ Architecture

```
Frontend (React)
      ↓
Backend API (Spring Boot)
      ↓
AI Agent (LangChain4j + Mistral API)
      ↓
MCP Tools Layer
      ↓
System Actions (Files, Commands, Code Generation)
      ↓
PostgreSQL (Memory & Logs)
```

---

## 🛠️ Stack technique

### Backend

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA

### AI

* LangChain4j
* Mistral API

### Database

* PostgreSQL

### Frontend

* React
* Tailwind CSS (optionnel)

---

## 📦 Structure du projet

```
ai-dev-agent-mcp/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── agent/
│   ├── tools/
│   ├── repository/
│   └── config/
│
├── frontend/
│   ├── components/
│   ├── pages/
│   └── services/
│
└── README.md
```

---

## ⚙️ Installation

### 1. Cloner le projet

```bash
git clone https://github.com/your-username/ai-dev-agent-mcp.git
cd ai-dev-agent-mcp
```

---

### 2. Backend (Spring Boot)

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

---

### 3. Frontend (React)

```bash
cd frontend
npm install
npm run dev
```

---

### 4. Base de données (PostgreSQL)

Créer la base :

```sql
CREATE DATABASE ai_agent_db;
```

Configurer `application.properties` :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ai_agent_db
spring.datasource.username=your_user
spring.datasource.password=your_password
```

---

### 5. Configuration API Mistral

```bash
export MISTRAL_API_KEY=your_api_key
```

---

## 🧠 Exemple de commandes

* "create Spring Boot controller User"
* "generate React navbar component"
* "create folder images"
* "move all png files to assets"

---

## 📈 Roadmap

* [ ] Parser simple (Phase 1)
* [ ] MCP Tools de base
* [ ] Intégration LLM (Mistral)
* [ ] Memory System
* [ ] Mode Proactif
* [ ] Feedback Loop
* [ ] UI avancée

---

## 👨‍💻 Auteur

* Noureddine Imgharn

---

## 📌 Licence

Projet open-source à but éducatif.
