def buildjar(){
    sh 'mvn package'
}

def deployment(){
    withCredentials([usernamePassword(
        credentialsId:'dockerhub repo',
        usernameVariable:'USER',
        passwordVariable:'PASS'
    )])
    {
        sh 'docker build -t krnmalde/jenkins:5.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push krnmalde/jenkins:5.0'
        echo "deploy application success
    }


}

return this
