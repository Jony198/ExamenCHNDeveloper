#!/bin/bash
set -e

echo "📋 Archivos en /app:"
ls -la /app

echo "🚀 Iniciando aplicación..."
exec java -jar app.jar
