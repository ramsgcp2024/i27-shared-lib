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

    def k8sdeploy(k8sfilename, namespace, docker_image) {
        jenkins.sh """
        echo "Executing k8s deploy"
        sed -i "s|DIT|${docker_image}|g" ./.cicd/$k8sfilename
        kubectl apply -f ./.cicd/$k8sfilename -n $namespace 
        """
    }

    def k8sHelmChartDeploy(appname, env, helmChartPath, imageTag) {
        jenkins.sh """
            echo " *********** Helm chart is calling ********* "
            helm version
            echo "Verifying if chart exists"
            if helm list | grep -q "${appname}-${env}-chart"; then
            echo "This chart exists !!!!!"
            echo "Upgrading HELM Chart as per requirement !!!!!!"
            helm upgrade ${appname}-${env}-chart -f ./cicd/k8s/values_${env}.yaml --set image.tag=${imageTag} ${helmChartPath}
            else
            echo " ****************** Helm Does not exists !!! ****************** "
            echo " ****************** Helm Installing new chart !!! ****************** "
            helm install ${appname}-${env}-chart -f ./.cicd/k8s/values_${env}.yaml --set image.tag=${imageTag} ${helmChartPath}
            fi
        """
    }

    def gitClone() {
        jenkins.sh """
        echo " ******** Executing git clone groovy method ************ "
        git clone -b main https://github.com/ramsgcp2024/i27-shared-lib.git
        echo "listing the file after clone"
        """
    }

    
}

//gcloud container clusters get-credentials cart-cluster --zone us-west1-a --project instant-droplet-410306

// # gcloud auth activate-service-account jenkins@instant-droplet-410306.iam.gserviceaccount.com --key-file=${}