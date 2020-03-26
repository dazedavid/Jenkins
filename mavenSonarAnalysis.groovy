node('demo'){
    def mvnHome = tool name: '363', type: 'maven'
    stage ('checkout'){
        git credentialsId: 'davidgithubaccount', url: 'https://github.com/dazedavid/simple-java-maven-app.git'
    }
    stage('Maven Test'){
        sh "$mvnHome/bin/mvn clean test surefire-report:report-only"
        //archiveArtifacts 'target/site/surefire-report.html'
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/site/', reportFiles: 'surefire-report.html', reportName: 'HTML Report', reportTitles: ''])
    }
    stage('SonarAnalysis'){
        sh "$mvnHome/bin/mvn sonar:sonar" 
    }
    stage('Maven Build'){
        sh "$mvnHome/bin/mvn clean package -DskipTests=true"
    }
}