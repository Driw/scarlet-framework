@NonCPS
def modules() {
	def  dirsl = [ "scarlet-core", "scarlet-logger", "scarlet-stream", "scarlet-context" ]
	dirsl
}

pipeline {
	agent any

	tools {
		maven "maven"
	}

	stages {
		stage('Checkout') {
			steps {
				git branch: "${env.BRANCH_NAME}", url: "${env.GIT_URL}"
			}
		}

		stage('Build') {
			steps {
				script {
					sh "mvn clean validate compile package install -Dmaven.test.skip=true -e"
				}
			}
		}

		stage('JUnit') {
			steps {
				script {
					sh "mvn test -Dmaven.test.redirectTestOutputToFile=true -e"
				}
			}
			post {
				always {
					script {
						for (module in modules()) {
							junit "${module}/target/surefire-reports/*.xml"
						}
					}
				}
			}
		}

		stage('SonarQube Scan') {
			steps {
				script {
					for (module in modules()) {
						dir("${env.WORKSPACE}/${module}") {
							withSonarQubeEnv("sonarqube-server") {
								sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin -e -X"
							}
						}
					}
				}
			}
		}

		stage("SonarQube Quality Gate") {
			steps {
				script {
					for (module in modules()) {
						dir("${env.WORKSPACE}/${module}") {
							script {
								timeout(time: 30, unit: 'SECONDS') {
									def response = waitForQualityGate()
									if (response.status != 'OK') {
										error "Pipeline aborted due to quality gate failure: ${qg.status}"
									}
								}
							}
						}
					}
				}
			}
		}

		stage("Publish to Nexus") {
			steps {
				script {
					for (module in modules()) {
						dir("${env.WORKSPACE}/${module}") {
							script {
								pom = readMavenPom file: "pom.xml";
								filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
								echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
								artifactPath = filesByGlob[0].path;
								artifactExists = fileExists artifactPath;
								if(artifactExists) {
									nexusPublisher nexusInstanceId: 'nexus3-login', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [], mavenCoordinate: [artifactId: pom.artifactId, groupId: pom.parent.groupId, packaging: pom.packaging, version: pom.version]]]
								} else {
									error "*** File: ${artifactPath}, could not be found";
								}
							}
						}
					}
				}
			}
		}
	}
}
