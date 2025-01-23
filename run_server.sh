#!/bin/zsh

# Dropwizard Email Service Builder and Runner
# Description: Builds and starts the EmailWizard service

# Variables
JAR_PATH="target/EmailWizard-1.0-SNAPSHOT.jar"
CONFIG_PATH="./config/app.yml"

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Function to build the project
build_project() {
  echo "${GREEN}Building project...${NC}"
  if mvn clean install; then
    echo "${GREEN}Build successful!${NC}"
  else
    echo "${RED}Build failed!${NC}"
    exit 1
  fi
}

# Function to package the project
package_project() {
  echo "${GREEN}Packaging project...${NC}"
  if mvn package; then
    echo "${GREEN}Packaging successful!${NC}"
  else
    echo "${RED}Packaging failed!${NC}"
    exit 1
  fi
}

# Function to run the application
run_application() {
  # Check if jar exists
  if [ ! -f "$JAR_PATH" ]; then
    echo "${RED}Error: JAR file not found at $JAR_PATH${NC}"
    exit 1
  fi

  # Check if config exists
  if [ ! -f "$CONFIG_PATH" ]; then
    echo "${RED}Error: Configuration file not found at $CONFIG_PATH${NC}"
    exit 1
  fi

  echo "${GREEN}Starting EmailWizard service...${NC}"
  java -jar "$JAR_PATH" server "$CONFIG_PATH"
}

# Main execution
echo "${GREEN}Starting EmailWizard build and run process...${NC}"
build_project
package_project
run_application
