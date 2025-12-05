runProgram:
		(cd backend && mvn clean package && mvn spring-boot:run)

		(cd frontend && npm run dev)
