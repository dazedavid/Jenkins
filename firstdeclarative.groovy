pipeline{
    agent {
        label "testagent"
    }
    environment {
        myName = "David"
    }
    stages{
        stage('Checkout'){
            steps {
                echo "Doing a git command here"
            }
        }
        stage('Build'){
            steps {
                echo "Variable name is $myName"
                sh "touch abc.txt"
            }
        }
        stage('Notify'){
            steps {
                echo "Doing a post build action here"
            }
        }
    }
}