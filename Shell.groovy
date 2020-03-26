node(){
    stage('Greetings'){
        echo "Hey there!"
    }
    stage('Introduction'){
        echo "My Name is David Maharjan"
        echo "I am staging Jenkins"
    }
    stage('Executing shell commands'){
        sh "ls -latr"
        sh "pwd"
        sh "whoami"
    }
    stage('Ending'){
        sh '''
            ls -latr
            pwd
            whoami
        '''
    }
    stage('Sleeping'){
        sh "sleep 60"
    }
}