#!/bin/bash

# Clean and package with Maven
mvn clean package

# Create a zip file of the project
zip -r library-management-system.zip src/ pom.xml README.md

echo "Project packaged successfully. The zip file is library-management-system.zip" 