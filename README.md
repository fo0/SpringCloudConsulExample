# SpringCloudConsulExample

### install consul "server" => main server
```bash
docker run -d -p 8500:8500 -p 8600:8600/udp --name=badger consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0
```

### install consul "agent" => listeners for managing the applications, this is the entry point for the services
```bash
docker run --name=fox consul agent -node=client-1 -join=172.17.0.2
```

### check installed consul applications at the main "server"
```bash
docker exec badger consul members
```
