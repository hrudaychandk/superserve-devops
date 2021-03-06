node {
    stage('SCM checkout') {
        git 'https://github.com/hrudaychandk/superserve-devops.git'
    }
    stage('Sonarqube analysis') {
        withSonarQubeEnv('sonarqube') {
            sh './gradlew sonarqube -x test'
        }
    }    
    stage ('Gradle build') {
        sh './gradlew bootJar'
    }
    stage ('Build and deploy docker container') {
        sh 'sudo docker build -t hrudaychandk/superserve_webapp:1.0 .'
        sh 'sudo docker run --name superserve -itd -p 3333:3333 hrudaychandk/superserve_webapp:1.0'
    }
    stage ('Selenium tests') {
        sh './gradlew clean test'
    }
    stage ("Destroy the container") {
        sh 'sudo docker stop superserve'
        sh 'sudo docker rm superserve'
    }
    stage ('Push image to DockerHub') {
        withCredentials([string(credentialsId: 'docker-hub-pwd', variable: 'dockerHubPwd')]) {
            sh "sudo docker login -u hrudaychandk -p ${dockerHubPwd}"
        }
        sh 'sudo docker push hrudaychandk/superserve_webapp:1.0'
    }
}
