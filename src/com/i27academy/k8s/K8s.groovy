package com.i27academy.k8s

class K8s {
    def jenkins 
    K8s(jenkins) {
        this.jenkins = jenkins
    }

    // Method to authoticate to cluster
    def auth_login(gke_cluster_name, gke_zone, gke_project) {
        jenkins.sh """
        echo "Connecting to Kubernates Cluster through ****JENKINS****" 
        gcloud compute instances list
        
        gcloud container clusters get-credentials $gke_cluster_name --zone $gke_zone --project $gke_project
        kubectl get nodes 
        """
    }

    def k8sdeploy(k8sfilename, namespace) {
        jenkins.sh """
        echo "Executing k8s deploy"
        kubectl apply -f ./.cicd/$k8sfilename -n $namespace 
        """
    }

//gcloud container clusters get-credentials cart-cluster --zone us-west1-a --project instant-droplet-410306

// # gcloud auth activate-service-account jenkins@instant-droplet-410306.iam.gserviceaccount.com --key-file=${}
