kafkaUp: ## Up local kafka
	    docker-compose -f kafka-compose.yml  up -d

kafkaDown: ## Down local kafka
		docker-compose -f kafka-compose.yml down

pgUp:
	docker-compose -f postgres-compose.yml  up -d
	./gradlew flywayMigrate

pgDown:
	docker-compose -f postgres-compose.yml  down

