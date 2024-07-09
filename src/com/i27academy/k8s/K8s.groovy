package com.i27academy.k8s

class k8s {
    def jenkins 
    K8s(jenkins) {
        this.jenkins = jenkins
    }

    // Method to authoticate to cluster
    def auth_login() {
        jenkins.sh """
        echo "Connecting to Kubernates Cluster through ****JENKINS****" 
        # gcloud auth activate-service-account jenkins@instant-droplet-410306.iam.gserviceaccount.com --key-file=${}
        gcloud compute instances list
        """
    }

}