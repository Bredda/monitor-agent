# Monitor Agent

Web Service exposant des informations sur le système local

Nécessite JDK >= 14

## Build

* Depuis la racine du projet (nécessite Maven):
`mvn clean install`

OU

* Depuis IntelliJ, lancer la configuration `Build Jar`

Le jar est produit dans le dossier `/target`

## Usage

### Launch

`java -jar monitor-agent-1.0.0`

Properties disponibles:
* `agentName` - nom pour cet agent. Il sera renvoyé dans les informations système. Défaut: `agentName not set`
* `server.port` - permet de spécifier le port du Web Service. Défaut `3000` 

`java -jar -DagentName=[nom de l'agent] -Dserver.port[port] monitor-agent-1.0.0`

### Exemple d'appel

`GET http://localhost:3000/monitor-agent`
```json
{
    "agent": "PO1",
    "date": "18/02/2022 10:00:15",
    "system": {
        "arch": "amd64",
        "version": "10.0",
        "name": "Windows 10"
    },
    "cpu": {
        "availableProcessors": 8,
        "cpuLoad": 0.10738270715756429,
        "cpuAverageLoad": 0.10738270715756429
    },
    "process": {
        "processCpuLoad": 0.10738270715756429,
        "processCpuTime": 9078125000,
        "processAllocatedMemory": 38132944,
        "processTotalMemory": 4215275520,
        "processPresumableFreeMemory": 4177142576,
        "processMemoryLoad": 0.00904637
    },
    "memory": {
        "committedVirtualSize": 231067648,
        "totalSwapSize": 29742899200,
        "freeSwapSize": 15672004608,
        "freeMemory": 5554331648,
        "totalMemory": 16857997312
    }
}
```