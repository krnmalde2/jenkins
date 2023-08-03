def buildjar(){
    sh 'mvn package'
    echo "Building jar file successfully"
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
        echo "deploy application successfully"
    }


}

return this
