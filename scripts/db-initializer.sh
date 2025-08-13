#!/bin/bash

set -e

echo "PostgreSQL is ready. Creating databases..."

psql -U "$POSTGRES_USER" -c "CREATE DATABASE catalogue;" || echo "Database 'catalogue' already exists"
