pipeline {
    agent  any
    tools {
        jdk 'openjdk8'
        maven '363'
    }
        
    stages {
        stage ('Test') {
            steps {
                sh "mvn clean test"
            }
        }
    }
    
}