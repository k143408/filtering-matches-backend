all: install test start
install:
	./mvnw clean install
start:
	./mvnw clean spring-boot:run
test:
	./mvnw clean verify
