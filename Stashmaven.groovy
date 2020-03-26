node('demo'){
    def mvnHome = tool name: '363', type: 'maven'
    def user = "ec2-user"
    def target = "172.31.32.220"
    stage ('checkout'){
        git credentialsId: 'davidgithubaccount', url: 'https://github.com/dazedavid/simple-java-maven-app.git'
    }
    stage('Maven Test'){
        sh "$mvnHome/bin/mvn clean test surefire-report:report-only"
        archiveArtifacts 'target/site/surefire-report.html'
    }
    stage('Maven Build'){
        sh "$mvnHome/bin/mvn clean package -DskipTests=true"
        stash includes: 'target/my-app-1-RELEASE.jar', name: 'myPackage'
    }
    timeout(2){
        stage('Deployment'){
            input 'Do you want me to prmote to Prod'
            unstash 'myPackage'
            sh "ls -latr"
            sshagent(['proddeploymentssshkey']){
                sh "scp -o StrictHostKeyChecking=no target/my-app-1-RELEASE.jar $user@$target:/tmp"
            }
        }
    }
}