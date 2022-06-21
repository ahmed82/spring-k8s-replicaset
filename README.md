# spring-k8s-replicaset
SpringBoot to Kubernetes with ReplicaSet.

![image](https://user-images.githubusercontent.com/9446035/173872670-b6633dbb-8443-40ab-8e7f-aaf302d2c7dd.png)

Kubernetes Master Components: Etcd, API Server, Controller Manager, and Scheduler

## Etcd [https://github.com/etcd-io/etcd]
etcd is a distributed reliable key-value store for the most critical data of a distributed system, with a focus on being:

- Simple: well-defined, user-facing API (gRPC)
- Secure: automatic TLS with optional client cert authentication
- Fast: benchmarked 10,000 writes/sec
- Reliable: properly distributed using Raft.
**Etcd** is written in Go and uses the Raft consensus algorithm to manage a highly-available replicated log. Raft is a consensus algorithm designed as an alternative to Paxos. The Consensus problem involves multiple servers agreeing on values; a common problem that arises in the context of replicated state machines. Raft defines three different roles (Leader, Follower, and Candidate) and achieves consensus via an elected leader. For further information, please read the (Raft paper)[https://raft.github.io/raft.pdf].

##**Etcdctl**[https://github.com/etcd-io/etcd/tree/main/etcdctl]
is the command-line interface tool written in Go that allows manipulating an etcd cluster. It can be used to perform a variety of actions, such as:

- Set, update and remove keys.
- Verify the cluster health.
- Add or remove etcd nodes.
- Generating database snapshots.
- You can play online with a 5-node etcd cluster at http://play.etcd.io.

## Scheduler
The Scheduler watches for unscheduled pods and binds them to nodes via the /binding pod subresource API, according to the availability of the requested resourcesThe Scheduler watches for unscheduled pods and binds them to nodes via the /binding pod subresource API, according to the availability of the requested resources. Once the pod has a node assigned, the regular behavior of the Kubelet is triggered and the pod and its containers are created.

## Controller Manager
The Kubernetes Controller Manager is a daemon that embeds the core control loops (also known as “controllers”) shipped with Kubernetes. Basically, a controller watches the state of the cluster through the API Server watch feature and, when it gets notified, it makes the necessary changes attempting to move the current state towards the desired state.

## API Server
When you interact with your Kubernetes cluster using the kubectl command-line interface, you are actually communicating with the master API Server component.

![image](https://user-images.githubusercontent.com/9446035/173851781-784f7b4d-cdd2-4ccd-9b94-b8040ca80734.png)

![image](https://user-images.githubusercontent.com/9446035/173920343-21a47c6a-b048-4aa7-b9b8-6249a281450a.png)

1. kubectl writes to the API Server.
1. API Server validates the request and persists it to etcd.
1. etcd notifies back the API Server.
1. API Server invokes the Scheduler.
1. Scheduler decides where to run the pod on and return that to the API Server.
1. API Server persists it to etcd.
1. etcd notifies back the API Server.
1. API Server invokes the Kubelet in the corresponding node.
1. Kubelet talks to the Docker daemon using the API over the Docker socket to create the container.
1. Kubelet updates the pod status to the API Server.
1. API Server persists the new state in etcd.

# Build the project

## Build a Docker Image 

1- Docker cmd build  `<Preferred>`
```
 docker build -t ahmedalsalih/spring-k8s:v2.2 .
```
or 

2-  Maven build
```
./mvnw spring-boot:build-image
```

## Run the image `FOR TEST ONLY`
```
docker run -p 9090:8080 ahmedalsalih/spring-k8s:v2.2
```

# Push the image to Docker Hub

![Push image](./src/main/resources/images/1-push-image.PNG)

![Docker Hub](./src/main/resources/images/2-docker-hub.PNG)


# Run Kubernetes
run git bash `As Admin` the type:
```
minikube start
```
![image](https://user-images.githubusercontent.com/9446035/174331881-e4215485-5444-480a-8dc1-e4176f9b92a6.png)

## Create deployment
```
kubectl create deployment springk8sapp --image=ahmedalsalih/spring-k8s:v2.2
```
![image](https://user-images.githubusercontent.com/9446035/174334238-562ac1f3-a4bc-4c42-aa6a-9bde37f5a04b.png)

`deployment and pod created as well.`

![k8s-level](./src/main/resources/images/k8s-level.PNG)


## kubectl

```
kubectl get pods
```

```
kubectl get replicaset
```

```
kubectl describe pod springk8sapp-64c6ff74cd-86hpv
```

`Success deployment creating`
![image](https://user-images.githubusercontent.com/9446035/174341559-a462d1b3-675e-4e07-8b6a-f87a54db36dc.png)


# Dashboard
```
minikube dashboard
```

## Scale a resource / Replicaset
```
kubectl scale -n default deployment springk8sapp --replicas=3
```

## Update deployment
```
kubectl apply -f <spec.yaml>
```
# Create deployment from form

![image](https://user-images.githubusercontent.com/9446035/174345299-862e0fe0-1259-4c35-82e8-33fd7a1fc0c4.png)

 
# Delete all deployment
```
kubectl delete --all deployments
```
**Checking** All three comends below should give `No resources found in default namespace.`

```
kubectl get pods
```
```
kubectl get deployment
```
```
kubectl get replicaset
```
# Create deployment `YAML` file
```
kubectl apply -f deployment.yaml
```



