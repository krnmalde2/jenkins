def buildjar(){
<<<<<<< HEAD
    sh 'mvn package'
    echo "Building jar file successfully and testing github webhook"
=======
    sh 'mvn build-helper:parse:version versions:set -DnewVersions= \\\
        ${parseVersion.nextMajorVersion}.\\\${parseVersion.minorVersion}.\\\${parseVersion.incrementalVersion}'
    def match = readFile('pom.xml')=~'<version>(.+)<version>'
    def version= match[0][1]
    echo "new version is $version"
    env.IMAGE_VERSION = "$version-$BUILD_NUMBER"


    sh 'mvn clean package'
>>>>>>> added version
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
<<<<<<< HEAD
        sh 'docker push krnmalde/jenkins:5.0'
        echo "deploy application successfully deployed"
=======
        sh "docker push krnmalde/jenkins:$IMAGE_VERSION"
>>>>>>> added version
    }


}

return this
