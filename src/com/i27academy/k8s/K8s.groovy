package com.i27academy.k8s

class K8s {
    def jenkins 
    K8s(jenkins) {
        this.jenkins = jenkins
    }

    // Method to authoticate to cluster
    def auth_login() {
        jenkins.sh """
        echo "Connecting to Kubernates Cluster through ****JENKINS****" 
        gcloud compute instances list
        gcloud container clusters get-credentials cart-cluster --zone us-west1-a --project instant-droplet-410306
        kubectl get nodes 
        """
    }

}

// # gcloud auth activate-service-account jenkins@instant-droplet-410306.iam.gserviceaccount.com --key-file=${}
