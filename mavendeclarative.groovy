pipeline {
    agent {
        label 'testagent'
    }
    tools {
        maven '363'
    }
    environment {
         githubcreds = credentials('davidgithubaccount')
    }

    stages {
        stage('Checkout'){
            steps {
                git credentialsId: 'davidgithubaccount', url: 'https://github.com/dazedavid/simple-java-maven-app.git'
            }
            
        }
        stage('Maven Build'){
            steps {
                sh "mvn clean package"
            }
        }
    }
}