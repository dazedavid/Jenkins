node(){
    def myName = 'David'
    stage('Checkout') {
        echo "Doing a git command here"
    }
    stage('Build'){
        echo "Variable name is $myName"
    }
    stage('Notify'){
        echo "Doing a post build action here"
    }
}