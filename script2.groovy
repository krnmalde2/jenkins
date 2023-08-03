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
        sh "docker build -t krnmalde/jenkins:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"

    
        echo "deploy application successfully deployed"
        sh "docker push krnmalde/jenkins:${IMAGE_NAME}"

    }


}

return this
