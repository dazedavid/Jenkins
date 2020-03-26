pipeline {
    agent {
        label 'testagent'
    }
    environment {
         githubcreds = credentials('davidgithubaccount')
    }

    stages {
        stage('Checkout'){
            steps {
                git credentialsId: 'davidgithubaccount', url: 'https://github.com/dazedavid/dazedavid'
            }
            
        }
        stage('Build'){
            steps {
                sh "sh file.sh"
            }

        }
    }
}