def buildjar(){

    sh 'mvn package'
    echo "Building jar file successfully and testing github webhook"

    


    sh 'mvn clean package'

}

def deployment(){
    withCredentials([usernamePassword(
        credentialsId:'dockerhub repo',
        usernameVariable:'USER',
        passwordVariable:'PASS'
    )])
    {
        sh "docker build -t krnmalde/jenkins:$IMAGE_VERSION ."
        sh "echo $PASS | docker login -u $USER --password-stdin"

        sh 'docker push krnmalde/jenkins:5.0'
        echo "deploy application successfully deployed"
        sh "docker push krnmalde/jenkins:$IMAGE_VERSION"

    }


}

return this
