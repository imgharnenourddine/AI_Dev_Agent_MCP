<div align="center">

```
 █████╗ ██╗    ██████╗ ███████╗██╗   ██╗     █████╗  ██████╗ ███████╗███╗   ██╗████████╗
██╔══██╗██║    ██╔══██╗██╔════╝██║   ██║    ██╔══██╗██╔════╝ ██╔════╝████╗  ██║╚══██╔══╝
███████║██║    ██║  ██║█████╗  ██║   ██║    ███████║██║  ███╗█████╗  ██╔██╗ ██║   ██║   
██╔══██║██║    ██║  ██║██╔══╝  ╚██╗ ██╔╝    ██╔══██║██║   ██║██╔══╝  ██║╚██╗██║   ██║   
██║  ██║██║    ██████╔╝███████╗ ╚████╔╝     ██║  ██║╚██████╔╝███████╗██║ ╚████║   ██║   
╚═╝  ╚═╝╚═╝    ╚═════╝ ╚══════╝  ╚═══╝      ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝   ╚═╝  
```

**Un agent IA de développement intelligent qui tourne dans votre terminal**

[![Node.js](https://img.shields.io/badge/Node.js-18+-339933?style=for-the-badge&logo=node.js&logoColor=white)](https://nodejs.org)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.0+-3178C6?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org)
[![MCP](https://img.shields.io/badge/MCP-Protocol-FF6B35?style=for-the-badge)](https://modelcontextprotocol.io)
[![Mistral](https://img.shields.io/badge/Mistral-AI-7C3AED?style=for-the-badge)](https://mistral.ai)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://postgresql.org)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

---

```bash
$ npm install -g ai-dev-agent
$ agent

🤖 AI Dev Agent v1.0.0 — Prêt !
agent> create Spring Boot controller User
✅ UserController.java généré avec succès !

💡 Suggestions :
   1. Créer UserService ?
   2. Créer UserRepository ?
   3. Créer User Entity ?
agent> _
```

</div>

---

## 📋 Table des matières

- [🚀 Présentation](#-présentation)
- [🧠 Comment ça fonctionne](#-comment-ça-fonctionne)
- [🏗️ Architecture](#️-architecture)
- [🔧 MCP Servers](#-mcp-servers)
- [💾 Memory System](#-memory-system)
- [⚡ Mode Proactif](#-mode-proactif)
- [🛠️ Stack Technique](#️-stack-technique)
- [📦 Structure du projet](#-structure-du-projet)
- [⚙️ Installation](#️-installation)
- [🎯 Commandes disponibles](#-commandes-disponibles)
- [🗓️ Roadmap](#️-roadmap)
- [👨‍💻 Auteur](#-auteur)

---

## 🚀 Présentation

**AI Dev Agent** est un assistant IA de développement qui tourne directement dans votre terminal. Il comprend vos commandes en langage naturel et les transforme en actions concrètes grâce à une architecture basée sur le **Model Context Protocol (MCP)**.

> Inspiré de Claude Code et GitHub Copilot CLI — mais open-source et entièrement personnalisable.

### ✨ Ce que l'agent peut faire

| Capacité | Description |
|---|---|
| 🧠 **Langage naturel** | Comprend vos commandes sans syntaxe spéciale |
| 📁 **Fichiers** | Créer, supprimer, déplacer, lister des fichiers |
| 🧱 **Code** | Générer du code Spring Boot et React |
| 💻 **Commandes** | Exécuter des commandes système (maven, npm, git...) |
| 💾 **Mémoire** | Se souvient de tout ce qu'il a fait |
| ⚡ **Proactif** | Suggère automatiquement les prochaines étapes |

---

## 🧠 Comment ça fonctionne

```
┌─────────────────────────────────────────────────────────┐
│                    VOUS (Terminal)                       │
│              agent> create controller User               │
└──────────────────────────┬──────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│                   AGENT CORE (TypeScript)                │
│                                                          │
│   1. Reçoit la commande                                  │
│   2. Envoie à Mistral AI pour analyser                   │
│   3. Mistral répond : { tool: "code-server",             │
│                         method: "generate_controller",   │
│                         params: { name: "User" } }       │
│   4. Choisit le bon MCP Server                           │
└──────┬──────────────┬──────────────┬────────────────────┘
       │              │              │
       ▼              ▼              ▼
┌──────────┐   ┌──────────┐   ┌──────────┐
│   FILE   │   │   CODE   │   │ COMMAND  │
│  SERVER  │   │  SERVER  │   │  SERVER  │
│   MCP    │   │   MCP    │   │   MCP    │
└──────────┘   └──────────┘   └──────────┘
       │              │              │
       └──────────────┴──────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                    PostgreSQL                            │
│              Mémoire & Historique                        │
└─────────────────────────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                    VOUS (Terminal)                       │
│   ✅ UserController.java généré !                        │
│   💡 Voulez-vous créer UserService ?                     │
└─────────────────────────────────────────────────────────┘
```

---

## 🏗️ Architecture

### Communication JSON-RPC entre Agent et MCP Servers

L'agent communique avec chaque MCP Server via **stdin/stdout** en utilisant le protocole **JSON-RPC 2.0** :

```
Agent TypeScript (processus parent)
         │
         ├── stdin  ──→  File MCP Server  (sous-processus)
         │   stdout ←──
         │
         ├── stdin  ──→  Code MCP Server  (sous-processus)
         │   stdout ←──
         │
         └── stdin  ──→  Command MCP Server (sous-processus)
             stdout ←──
```

**Requête JSON-RPC (Agent → MCP Server) :**
```json
{
  "jsonrpc": "2.0",
  "id": 1,
  "method": "generate_controller",
  "params": {
    "name": "User"
  }
}
```

**Réponse JSON-RPC (MCP Server → Agent) :**
```json
{
  "jsonrpc": "2.0",
  "id": 1,
  "result": "✅ UserController.java généré !"
}
```

---

## 🔧 MCP Servers

### 📁 File MCP Server

Gère toutes les opérations sur les fichiers et dossiers.

| Méthode | Paramètres | Description |
|---|---|---|
| `create_file` | `fileName`, `content`, `directory?` | Créer un fichier |
| `delete_file` | `fileName` | Supprimer un fichier |
| `move_file` | `source`, `destination` | Déplacer un fichier |
| `list_files` | `directory` | Lister les fichiers |
| `read_file` | `fileName` | Lire le contenu |

```bash
agent> create file README.md
agent> move images/ to assets/
agent> list files in src/
```

---

### 🧱 Code MCP Server

Génère du code boilerplate pour Spring Boot et React.

| Méthode | Paramètres | Description |
|---|---|---|
| `generate_controller` | `name` | Controller Spring Boot |
| `generate_service` | `name` | Service Spring Boot |
| `generate_entity` | `name` | Entity JPA |
| `generate_repository` | `name` | Repository JPA |
| `generate_component` | `name` | Composant React |
| `generate_page` | `name` | Page React |

```bash
agent> create Spring Boot controller User
agent> create Spring Boot service Product
agent> generate React component Navbar
agent> generate React page Dashboard
```

---

### 💻 Command MCP Server

Exécute des commandes système dans votre terminal.

| Méthode | Paramètres | Description |
|---|---|---|
| `run_command` | `command`, `directory?` | Commande système |
| `run_maven` | `goal` | Commande Maven |
| `run_npm` | `script` | Script NPM |
| `run_git` | `command` | Commande Git |

```bash
agent> run mvn clean install
agent> run npm install
agent> run git init
agent> run git add .
```

---

## 💾 Memory System

L'agent mémorise toutes ses actions dans PostgreSQL pour améliorer ses réponses futures.

### Schéma de la base de données

```sql
-- Historique des commandes
CREATE TABLE command_history (
    id          SERIAL PRIMARY KEY,
    user_input  TEXT          NOT NULL,
    tool_used   VARCHAR(50),
    method      VARCHAR(100),
    params      JSONB,
    result      TEXT,
    success     BOOLEAN       DEFAULT true,
    created_at  TIMESTAMP     DEFAULT NOW()
);

-- Fichiers générés
CREATE TABLE generated_files (
    id          SERIAL PRIMARY KEY,
    file_name   VARCHAR(200)  NOT NULL,
    file_path   TEXT,
    file_type   VARCHAR(50),
    content     TEXT,
    created_at  TIMESTAMP     DEFAULT NOW()
);

-- Contexte de session
CREATE TABLE session_context (
    id           SERIAL PRIMARY KEY,
    session_id   VARCHAR(100),
    context_data JSONB,
    updated_at   TIMESTAMP     DEFAULT NOW()
);
```

---

## ⚡ Mode Proactif

Après chaque action, l'agent analyse ce qui a été fait et suggère automatiquement les prochaines étapes logiques.

```bash
agent> create Spring Boot controller User

✅ UserController.java généré avec succès !

┌─────────────────────────────────────┐
│  💡 Suggestions intelligentes        │
├─────────────────────────────────────┤
│  1. Créer UserService               │
│  2. Créer UserRepository            │
│  3. Créer User Entity               │
└─────────────────────────────────────┘

agent> _
```

---

## 🛠️ Stack Technique

```
┌─────────────────────────────────────────────┐
│              STACK TECHNIQUE                 │
├──────────────────┬──────────────────────────┤
│ Runtime          │ Node.js 18+              │
│ Langage          │ TypeScript 5.0+          │
│ CLI              │ Commander.js             │
│ LLM              │ Mistral AI API           │
│ MCP Protocol     │ @modelcontextprotocol/sdk│
│ Base de données  │ PostgreSQL 15+           │
│ ORM              │ Prisma                   │
│ Distribution     │ NPM (npm install -g)     │
└──────────────────┴──────────────────────────┘
```

---

## 📦 Structure du projet

```
ai-dev-agent-mcp/
│
├── src/                              ← Agent principal (TypeScript)
│   ├── cli.ts                        ← Point d'entrée CLI
│   ├── agent.ts                      ← Cerveau IA (Mistral)
│   ├── memory.ts                     ← Gestion PostgreSQL
│   └── router.ts                     ← Router vers les MCP Servers
│
├── mcp-servers/                      ← Vrais MCP Servers
│   ├── file-server/
│   │   ├── index.ts                  ← File MCP Server
│   │   └── package.json
│   ├── code-server/
│   │   ├── index.ts                  ← Code MCP Server
│   │   └── package.json
│   └── command-server/
│       ├── index.ts                  ← Command MCP Server
│       └── package.json
│
├── prisma/
│   └── schema.prisma                 ← Schéma PostgreSQL
│
├── package.json
├── tsconfig.json
└── README.md
```

---

## ⚙️ Installation

### Prérequis

- Node.js 18+
- PostgreSQL 15+
- Clé API Mistral

### Installation rapide (recommandé)

```bash
npm install -g ai-dev-agent
```

### Installation depuis les sources

```bash
# 1. Cloner le projet
git clone https://github.com/noureddine-imgharn/ai-dev-agent-mcp.git
cd ai-dev-agent-mcp

# 2. Installer les dépendances
npm install

# 3. Configurer les variables d'environnement
cp .env.example .env
```

Configurer `.env` :
```env
MISTRAL_API_KEY=your_mistral_api_key
DATABASE_URL=postgresql://user:password@localhost:5432/ai_agent_db
```

```bash
# 4. Initialiser la base de données
npx prisma migrate dev

# 5. Compiler TypeScript
npm run build

# 6. Lancer l'agent
npm start
```

### Intégration Claude Desktop

Pour utiliser vos MCP Servers dans Claude Desktop, ajoutez dans `claude_desktop_config.json` :

```json
{
  "mcpServers": {
    "file-server": {
      "command": "node",
      "args": ["/chemin/vers/mcp-servers/file-server/index.js"]
    },
    "code-server": {
      "command": "node",
      "args": ["/chemin/vers/mcp-servers/code-server/index.js"]
    },
    "command-server": {
      "command": "node",
      "args": ["/chemin/vers/mcp-servers/command-server/index.js"]
    }
  }
}
```

---

## 🎯 Commandes disponibles

### Commandes agent

```bash
agent> help                          # Afficher l'aide
agent> history                       # Voir l'historique
agent> clear                         # Vider l'écran
agent> exit                          # Quitter
```

### Exemples de commandes naturelles

```bash
# Fichiers
agent> create file Main.java
agent> delete file old.txt
agent> move images/ to assets/
agent> list files in src/

# Code Spring Boot
agent> create Spring Boot controller User
agent> create Spring Boot service Product
agent> create Spring Boot entity Order
agent> create Spring Boot repository Customer

# Code React
agent> generate React component Navbar
agent> generate React page Dashboard
agent> generate React component LoginForm

# Commandes système
agent> run mvn clean install
agent> run npm install
agent> run git init
agent> run git add .
agent> run git commit -m "initial commit"
```

---

## 🗓️ Roadmap

```
✅ Phase 1 — CLI de base
   └── Boucle de commandes dans le terminal

✅ Phase 2 — Agent Core
   └── Intégration Mistral AI

🔄 Phase 3 — MCP Servers (en cours)
   ├── File MCP Server
   ├── Code MCP Server
   └── Command MCP Server

⏳ Phase 4 — Memory System
   └── PostgreSQL + Prisma

⏳ Phase 5 — Mode Proactif
   └── Suggestions intelligentes

⏳ Phase 6 — Distribution
   └── Publication sur NPM

⏳ Phase 7 — Analyzer Tool
   └── Analyser et améliorer le code existant
```

---

## 👨‍💻 Auteur

<div align="center">

**Noureddine Imgharn**

[![GitHub](https://img.shields.io/badge/GitHub-noureddine--imgharn-181717?style=for-the-badge&logo=github)](https://github.com/noureddine-imgharn)

</div>

---

<div align="center">

📌 **Projet open-source à but éducatif**

⭐ Si ce projet t'a aidé, laisse une étoile !

</div>
