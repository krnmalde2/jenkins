def buildjar(){

    sh 'mvn package'
    echo "Building jar file successfully and testing github webhook"

    


    sh 'mvn clean package'

}

// def deployment(){
    


// }

return this
