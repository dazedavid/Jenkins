node('demo'){
    def mvnHome = tool name: '363', type: 'maven'
    stage ('checkout'){
        git credentialsId: 'davidgithubaccount', url: 'https://github.com/dazedavid/simple-java-maven-app.git'
    }
    stage('Maven Build'){
        sh "$mvnHome/bin/mvn clean package"
    }
}