#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

echo "🚀 Starting clean build and deployment..."

# 1. Clean and package the application using Maven
echo "📦 Running Maven clean package..."
./mvnw clean package -DskipTests || mvn clean package -DskipTests

# 2. Rebuild and restart Docker containers
echo "🐳 Rebuilding Docker image..."
docker-compose build

echo "🐳 Starting Docker containers..."
# --force-recreate: Recreate containers even if their configuration and image haven't changed.
# --remove-orphans: Remove containers for services not defined in the Compose file.
docker-compose up -d --force-recreate --remove-orphans

echo "✅ Deployment successful! Logs follow:"
docker-compose logs -f
