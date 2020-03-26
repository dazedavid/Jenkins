node('demo'){
    properties([[$class: 'JiraProjectProperty'], parameters([choice(choices: ['dev', 'test', 'prod'], description: 'Choose the environment you want to put your package into ', name: 'targetEnv')])])
    def mvnHome = tool name: '363', type: 'maven'
    def user = "ec2-user"
    def target = ""
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
            getTarget(targetEnv) //calling function
            sshagent(['proddeploymentssshkey']) {
                sh "scp -o StrictHostKeyChecking=no target/my-app-1-RELEASE.jar $user@$target:/tmp"
            }
        }
    }
}

def getTarget(params) {
    if (params == 'dev') {
        target = "172.31.32.220"
    }
    else if(params == 'test') {
         target = "173.13.13.111"
    } 
    else if (params == 'prod') {
        target = "172.20.22.222"
    }
}