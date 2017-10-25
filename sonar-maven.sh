#!/bin/bash

mvn clean install sonar:sonar -Dsonar.host.url=https:/sonarqube.com -Dsonar.organization=sbaltes-github -Dsonar.login=SONARCLOUD_AUTHENTICATION_TOKEN
