mvn clean test sonar:sonar -Dsonar.host.url=http://kubernetes.docker.internal:33002/ -Dsonar.login=admin -Dsonar.password=admin -Dmaven.test.redirectTestOutputToFile=true -e
